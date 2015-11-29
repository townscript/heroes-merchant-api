package com.townscript.hero.restcontroller.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.townscript.hero.model.application.ApplicationData;
import com.townscript.hero.model.application.PaymentGatewayMap;
import com.townscript.hero.service.application.ApplicationDataService;

@RequestMapping("/application")
@Controller
public class ApplicationDataController {

	private ApplicationDataService applicationDataService = null;
	
	public ApplicationDataController() {
		if (applicationDataService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"com/townscript/hero/main-bean.xml");
			applicationDataService = (ApplicationDataService) context
					.getBean("ApplicationDataServiceImpl");
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addApplication(@RequestParam(value="json_data",required=true) String jsonRequest){

		try{
				
			System.out.println("Adding method");
	
			ApplicationData applicationData = new Gson().fromJson(jsonRequest, ApplicationData.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<ApplicationData>> constraintViolations = validator
					.validate(applicationData);
			ArrayList<ConstraintViolation<ApplicationData>> list = new ArrayList<ConstraintViolation<ApplicationData>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int applicationId = applicationDataService.addApplicationData(applicationData);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", applicationId);
			return obj;
			
		}catch (Exception e) {
	
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateApplication(@RequestParam(value="json_data",required=true) String jsonRequest){

		try{
				
			System.out.println("updating method");
	
			ApplicationData applicationData = new Gson().fromJson(jsonRequest, ApplicationData.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<ApplicationData>> constraintViolations = validator
					.validate(applicationData);
			ArrayList<ConstraintViolation<ApplicationData>> list = new ArrayList<ConstraintViolation<ApplicationData>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int applicationId = applicationDataService.updateApplicationData(applicationData);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", applicationId);
			return obj;
			
		}catch (Exception e) {
	
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadApplication(@RequestParam(value="appid",required=true) String id){
		
		try {
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "application Id must be in integer format");
				return obj;
			}
			
			Integer applicationId = Integer.parseInt(id);
			
			ApplicationData applicationData = applicationDataService.loadApplicationData(applicationId);
			String s = new Gson().toJson(applicationData);

			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The get request for loading the applicationData :- {}.",applicationData);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getpaymentoptions", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadPaymentOptionsForApplication(@RequestParam(value="appid",required=true) String id){
		
		try {
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "application Id must be in integer format");
				return obj;
			}
			
			Integer applicationId = Integer.parseInt(id);
			
			ApplicationData applicationData = applicationDataService.loadApplicationData(applicationId);
			
			List<String> paymentOptions = new ArrayList<String>();
			List<PaymentGatewayMap> map = applicationData.getPaymentGatewayMaps();
			for (PaymentGatewayMap paymentGatewayMap : map) {
				paymentOptions.add(paymentGatewayMap.getPaymentOption());
			}
			String s = new Gson().toJson(paymentOptions);

			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The get request for loading the applicationData :- {}.",applicationData);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadAllApplicationForMerchant(@RequestParam(value="merchantid",required=true) String id){
		
		try {
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "merchant Id must be in integer format");
				return obj;
			}
			
			Integer merchantId = Integer.parseInt(id);
			
			List<ApplicationData> applicationData = applicationDataService.loadAllApplicationForMerchant(merchantId);
			String s = new Gson().toJson(applicationData);

			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The get request for loading the applicationData :- {}.",applicationData);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteApplicationDate(@RequestParam(value="appid",required=true) String id){
			
		try {
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "Application Id must be in Integer Format");
				return obj;
			}
				
			Integer applicationId = Integer.parseInt(id);
			
			applicationDataService.deleteApplicationData(applicationId);
				
			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The post request for delete the applicationData for application id:- {}.",id);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", applicationId);
			return obj;
		} catch (Exception e) {
				
			e.printStackTrace();
			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			Logger errorLogger = LoggerFactory.getLogger("errorlogs");
			logger.error("The post request for delete the applicationData for application id:- {}.",id);
			errorLogger.error("TThe post request for delete the applicationData for application id {}{}.",id,e.getMessage());
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
		
	}
	
	
	
}
