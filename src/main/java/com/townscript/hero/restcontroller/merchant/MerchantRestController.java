package com.townscript.hero.restcontroller.merchant;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.townscript.hero.model.merchant.MerchantUserData;
import com.townscript.hero.service.merchant.MerchantUserDataService;

@RequestMapping("/merchant")
@Controller
public class MerchantRestController {

	private MerchantUserDataService merchantUserDataService = null;
	
	
	public MerchantRestController() {
		if (merchantUserDataService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"com/townscript/hero/main-bean.xml");
			merchantUserDataService = (MerchantUserDataService) context
					.getBean("MerchantUserDataServiceImpl");
		}
	}
	
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addMerchant(@RequestParam(value="json_data",required=true) String jsonRequest){
		try{
			System.out.println("Adding method");

			MerchantUserData merchantUserData = new Gson().fromJson(jsonRequest, MerchantUserData.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<MerchantUserData>> constraintViolations = validator
					.validate(merchantUserData);
			ArrayList<ConstraintViolation<MerchantUserData>> list = new ArrayList<ConstraintViolation<MerchantUserData>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int merchantId = merchantUserDataService.addMerchant(merchantUserData);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", merchantId);
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
	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadMerchantById(@RequestParam(value="id",required=true) String id){
		
		if (!id.matches("\\d+")) {
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 402);
			obj.put("message", "Merchant Id must be in integer format");
			return obj;
		}
		try {
			Integer merchantId = Integer.parseInt(id);
			MerchantUserData merchantUserData = merchantUserDataService.loadMerchantUserData(merchantId);
			String s = new Gson().toJson(merchantUserData);
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			logger.debug("The get request for loading the merchant :- {}.",merchantUserData);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			Logger errorLogger = LoggerFactory.getLogger("errorlogs");
			logger.error("The Get Request load merchant called for id {}. ",id);
			errorLogger.error("The Get Request load merchant called for id {}{}.",id,e);

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
	public @ResponseBody JSONObject loadMerchant(@RequestParam(value="emailid",required=true) String email){
		
		try {
			MerchantUserData merchantUserData = merchantUserDataService.loadMerchantUserData(email);
			String s = new Gson().toJson(merchantUserData);
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			logger.debug("The get request for loading the merchant :- {}.",merchantUserData);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			Logger errorLogger = LoggerFactory.getLogger("errorlogs");
			logger.error("The Get Request load merchant called for emailId {}. ",email);
			errorLogger.error("The Get Request load merchant called for emailId {}{}.",email,e);

			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/isexist", method = RequestMethod.GET)
	public @ResponseBody JSONObject isMerchantExist(@RequestParam(value="emailid",required=true) String email){
		
		try {
			boolean isExist = merchantUserDataService.isMerchantExist(email);
			String s = new Gson().toJson(isExist);
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			logger.debug("The get request for checking the merchant is exist or not for email id:- {}.",email);

			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("data", s);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = LoggerFactory.getLogger(MerchantRestController.class);
			Logger errorLogger = LoggerFactory.getLogger("errorlogs");
			logger.error("The get request for checking the merchant is exist or not for email id {}. ",email);
			errorLogger.error("The get request for checking the merchant is exist or not for email id {}{}.",email,e);

			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("result", "Error");
			obj.put("code", 401);
			obj.put("message", e.getMessage());
			return obj;
		}
	
	}
	
}
