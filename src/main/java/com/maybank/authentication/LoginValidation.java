package com.maybank.authentication;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class LoginValidation {
	
	
	
	@Value("${application.login.userId}")
	private String userId;
	
	@Value("${application.login.password}")
	private String password;
	
	public Map<String, String> validateLogin(String username, String password)
	{
		
		Map<String, String> userDetails = new HashMap<String, String>();
		
		try{
			
			if(username == null  || password == null || "".equals(password) || "".equals(username) )
			{
				return null;
			}
			
			if(!userId.equals(username) || !this.password.equals(password))
			{
				return null;
			}
			userDetails.put("userFirstName", "Test Boo FirstName ");
			userDetails.put("lastName", "Boo LastName");
			userDetails.put("email", "test@maybank.com");
			userDetails.put("department", "Acturials");
			return userDetails;
			
		}
		catch (Exception e){
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	
	
}
