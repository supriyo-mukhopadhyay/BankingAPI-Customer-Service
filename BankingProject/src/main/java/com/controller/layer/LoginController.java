package com.controller.layer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exception.InvalidAuthException;
import com.pojo.UserBankDetails;
import com.service.layer.ServiceLayer;


@Controller
public class LoginController {

	private ServiceLayer service;
	@Autowired
	public void setService(ServiceLayer service) {
		this.service = service;
	}
	
	
	@RequestMapping(value="home.htm")
	public ModelAndView showPages()
	{
		System.out.println("hello");
		ModelAndView modelAndView = new ModelAndView("HomePage");
		modelAndView.addObject("Message", "Click the button to proceed");
		return modelAndView;
	}

	@RequestMapping(value = "login.htm")
	public ModelAndView loginController(HttpServletRequest req)
	{
		String forwardPath = req.getParameter("action");
		String page = "";
		System.out.println(forwardPath);
		if(forwardPath.equals("Login"))
		{
			page = "LoginPage";
		}
		
		else if(forwardPath.equals("Register"))
		{
			page = "RegistrationPage";
		}
		ModelAndView modelAndView = new ModelAndView(page);
		modelAndView.addObject("Message", "Welcome to Login Page");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "welcome.htm")
	public ModelAndView welcomeController(HttpServletRequest req)
	{
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		
		try{
			service.auth(uid, pwd);
			return new ModelAndView("Welcome","uid",uid);
		}
		catch (InvalidAuthException e) {
			return new ModelAndView("LoginPage", "errMsg", e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "bank.htm")
	public ModelAndView bankingDetails(HttpServletRequest req) throws Exception
	{
		String action = req.getParameter("UpdateDetails");
		int uid = Integer.valueOf(req.getParameter("uid"));
		int ifsc = Integer.valueOf(req.getParameter("ifsc"));
		UserBankDetails bankDetails = service.setUserBankDetails(uid, ifsc);
		StringBuilder response = new StringBuilder();
		response.append("Balance"+bankDetails.getBalance());
		response.append("Name"+bankDetails.getName());
		response.append("Acc"+bankDetails.getAccountNumber());
		response.append("Bank NAme"+bankDetails.getBankName());
	//UserBankDetails bankDetails  = 
		return new ModelAndView("BankingDetails","Response",response
				+ "");
	}
	
	@RequestMapping(value = "register.htm")
	public ModelAndView registerController(HttpServletRequest req)
	{
		int uid = Integer.valueOf(req.getParameter("uid"));
		
		
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String bankP = req.getParameter("bank");
		service.insertNewUser(name,pwd,uid);		
		int accNo = Integer.valueOf(req.getParameter("acc"));
		int balance = Integer.valueOf(req.getParameter("bal"));
		int ifsc = service.bankIfsc(bankP);
		service.userRegistration(ifsc,accNo, balance, uid);
		ModelAndView modelAndView = new ModelAndView("BankingDetails");
		modelAndView.addObject("Response", "Successfull");
		return modelAndView;
	}
	
	
}