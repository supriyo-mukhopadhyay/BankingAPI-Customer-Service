package com.controller.layer;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping({"home.htm"})
	public ModelAndView showPages()
	{
		ModelAndView modelAndView = new ModelAndView("HomePage");
		modelAndView.addObject("Message", "Welcome to Login Page");
		return modelAndView;
	}
//	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
//	public void showLogInPage() {
//	
//	}
	@RequestMapping(value = "login.htm")
	public ModelAndView loginController()
	{
		ModelAndView modelAndView = new ModelAndView("LoginPage");
		modelAndView.addObject("Message", "Welcome to Login Page");
		return modelAndView;
	}
}
