package com.maybank.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	@Value("${session.timeout}")
	private int sessionTimeout;
	
	private static final Logger _log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		request.getSession().setMaxInactiveInterval(sessionTimeout);
		CustomUsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
  		setDetails(request, authRequest);
	  
  		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	private CustomUsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
	    String username = obtainUsername(request);
	    String password = obtainPassword(request);
	    _log.info("UserName IN Filter : "+username);
	    _log.info("UserName IN Filter : "+password);
	    
	    return new CustomUsernamePasswordAuthenticationToken(username, password);
  	}
  
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	     super.setAuthenticationManager(authenticationManager);
	}
	
	
	@Override
	@Autowired
	public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionStrategy) {
		_log.debug("++[SecurityConfiguration.setSessionAuthenticationStrategy() called]++");
		super.setSessionAuthenticationStrategy(sessionStrategy);
	}
	
	@Override
	@Autowired
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {

		super.setAuthenticationFailureHandler(failureHandler);
	}
}
