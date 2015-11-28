package com.townscript.hero.restcontroller.merchant;

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
import com.townscript.hero.model.merchant.MerchantAccountDetails;
import com.townscript.hero.restcontroller.application.ApplicationDataController;
import com.townscript.hero.service.merchant.MerchantAccountDetailService;

@RequestMapping("/account")
@Controller
public class MerchantAccountDetailController {

	private MerchantAccountDetailService merchantAccountDetailService = null;
	
	public MerchantAccountDetailController() {
		if (merchantAccountDetailService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"com/townscript/hero/main-bean.xml");
			merchantAccountDetailService = (MerchantAccountDetailService) context
					.getBean("MerchantAccountDetailServiceImpl");
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addApplication(@RequestParam(value="json_data",required=true) String jsonRequest){

		try{
				
			System.out.println("Adding account method");
	
			MerchantAccountDetails merchantAccountDetails = new Gson().fromJson(jsonRequest, MerchantAccountDetails.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<MerchantAccountDetails>> constraintViolations = validator
					.validate(merchantAccountDetails);
			ArrayList<ConstraintViolation<MerchantAccountDetails>> list = new ArrayList<ConstraintViolation<MerchantAccountDetails>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int accountId = merchantAccountDetailService.addMerchantAccount(merchantAccountDetails);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", accountId);
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
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateMerchantAccountDetails(@RequestParam(value="json_data",required=true) String jsonRequest){

		try{
				
			System.out.println("updating method");
	
			MerchantAccountDetails merchantAccountDetails = new Gson().fromJson(jsonRequest, MerchantAccountDetails.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<MerchantAccountDetails>> constraintViolations = validator
					.validate(merchantAccountDetails);
			ArrayList<ConstraintViolation<MerchantAccountDetails>> list = new ArrayList<ConstraintViolation<MerchantAccountDetails>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int accountId = merchantAccountDetailService.updateMerchantAccount(merchantAccountDetails);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", accountId);
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
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadMerchantAccountDetails(@RequestParam(value="accountid",required=true) String id){
		try {
			
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "Account Id must be in integer format");
				return obj;
			}
			
			Integer accountId = Integer.parseInt(id);
			
			MerchantAccountDetails merchantAccountDetails = merchantAccountDetailService.loadAccountDetails(accountId);
			String s = new Gson().toJson(merchantAccountDetails);

			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The get request for loading the merchantAccountDetails :- {}.",merchantAccountDetails);

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
	
	@SuppressWarnings({ "unchecked" })
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody JSONObject loadAllMerchantAccountDetails(@RequestParam(value="merchantid",required=true) String id){
		try {
			
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "Merchant Id must be in integer format");
				return obj;
			}
			
			Integer merchantId = Integer.parseInt(id);
			
			List<MerchantAccountDetails> merchantAccountDetailsList = merchantAccountDetailService.loadAllAccountDetails(merchantId);
			String s = new Gson().toJson(merchantAccountDetailsList);

			Logger logger = LoggerFactory.getLogger(ApplicationDataController.class);
			logger.debug("The get request for loading all merchant Account Details for merchantId:- {}.",id);

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
}
