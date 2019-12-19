package com.maybank.authentication;


import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.stereotype.Component;

import com.maybank.vo.UserVO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	LoginValidation loginValidation;
	
	
	private static final Logger _log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		

		CustomUsernamePasswordAuthenticationToken token = (CustomUsernamePasswordAuthenticationToken)authentication;
		String userName = token.getName();
		String password = token.getCredentials().toString();
		
		_log.info("[CustomAuthentication] userName:{}, password:{} ", userName,password);
		
		UserVO vo = new UserVO();
		UsernamePasswordAuthenticationToken tt = null;
		try {
			
			
			Map<String, String> map= loginValidation.validateLogin(token.getName(), 
					token.getCredentials().toString());
			
			if(map==null)
			{
				token =new CustomUsernamePasswordAuthenticationToken(null,"");
				token.setAuthenticated(false);
				
				throw new BadCredentialsException("Credentials are invalid");
			}
			
						
			vo.setFirstName(map.get("userFirstName"));
			vo.setUsername(map.get("lastName"));
			vo.setEmail(map.get("email"));
			vo.setDepartment(map.get("department"));
			
			
			tt= new UsernamePasswordAuthenticationToken(vo, "",null);
		
		}

		catch (Exception e) {

			_log.error("Exception: ", e);
			throw new BadCredentialsException("Internal Server Failed to Validate Credentials ");
		}
		
		
			return tt;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return (CustomUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	
	
	
}
