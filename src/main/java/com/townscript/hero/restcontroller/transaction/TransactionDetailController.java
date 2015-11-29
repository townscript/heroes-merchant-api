package com.townscript.hero.restcontroller.transaction;

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
import com.townscript.hero.model.transaction.TransactionDetails;
import com.townscript.hero.service.transaction.TransactionDetailService;

@RequestMapping("/transaction")
@Controller
public class TransactionDetailController {

	private TransactionDetailService transactionDetailService = null;
	public TransactionDetailController() {
		if (transactionDetailService == null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"com/townscript/hero/main-bean.xml");
			transactionDetailService = (TransactionDetailService) context
					.getBean("TransactionDetailServiceImpl");
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addApplication(@RequestParam(value="json_data",required=true) String jsonRequest){

		try{
				
			System.out.println("Adding method");
	
			TransactionDetails transactionDetails = new Gson().fromJson(jsonRequest, TransactionDetails.class);
			
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<TransactionDetails>> constraintViolations = validator
					.validate(transactionDetails);
			ArrayList<ConstraintViolation<TransactionDetails>> list = new ArrayList<ConstraintViolation<TransactionDetails>>();
			list.addAll(constraintViolations);
			
			if(list.size() != 0) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", list.get(0).getMessage());
				return obj;
			}
			
			int txnId = transactionDetailService.addTransaction(transactionDetails);
			
			JSONObject obj = new JSONObject();
			obj.put("result", "Success");
			obj.put("Id", txnId);
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
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllTransactionDetails(@RequestParam(value="merchantid",required=true) String id){
		try {
			if (!id.matches("\\d+")) {
				JSONObject obj = new JSONObject();
				obj.put("result", "Error");
				obj.put("code", 402);
				obj.put("message", "Merchant Id must be in integer format");
				return obj;
			}
			
			Integer merchantId = Integer.parseInt(id);
			
			List<TransactionDetails> transactionDetailsList = transactionDetailService.loadTransactionDetails(merchantId);
			String s = new Gson().toJson(transactionDetailsList);

			Logger logger = LoggerFactory.getLogger(TransactionDetailController.class);
			logger.debug("The get request for loading the transactions for merchant id :- {}.",id);

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
