package com.maybank.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {


	public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
			
		super(principal, credentials);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
