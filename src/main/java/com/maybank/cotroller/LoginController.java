package com.maybank.cotroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.maybank.vo.LoginForm;


@Controller
public class LoginController {



	private static final String loginPagePath="/login/index.html";
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@GetMapping(path = { "/loginPage" } )
	public String home(Model model) {

		model.addAttribute("loginForm", new LoginForm());
		if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
		{
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			return loginPagePath;
		}
		else
			if(null != SecurityContextHolder.getContext().getAuthentication())
				return "welcome";
			else
				return loginPagePath;
	}

	@RequestMapping("/")
	public String home() {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			LOGGER.debug("++[AdministrationController.home() method called isUserAuthenticated	: false ]++");
			return loginPagePath;
		}	
		else {
			if(null != SecurityContextHolder.getContext().getAuthentication()) {
				LOGGER.debug("++[AdministrationController.home() method called isUserAuthenticated	: true ]++");
				return "welcome";
			}	
			else {
				LOGGER.debug("++[AdministrationController.home() method called isUserAuthenticated	: false ]++");
				return loginPagePath;
			}	
		}	
	}
	
	
	@RequestMapping(path= {"/home"})
	public String validateLogin()
	{
				
		return "welcome";
	}
	
	
	
	
	
	
	
}
