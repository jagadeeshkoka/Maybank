package com.maybank.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.maybank.authentication.AjaxAwareAuthenticationEntryPoint;
import com.maybank.authentication.CustomAuthFailureHandler;
import com.maybank.authentication.CustomAuthenticationFilter;
import com.maybank.authentication.CustomAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private CustomAuthenticationProvider provider;
	
	@Autowired
	private CustomAuthFailureHandler customAuthenticationFailureHandler;
	

	@Value("${concurrent.session.number}")
	private int noOfConcurrentSesion;
	
	@Value("${error.after.maximum.session}")
	private boolean errorAfterMaximumSession;
	
	private static final Logger _log = LoggerFactory.getLogger(SecurityConfiguration.class);
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(provider);
		
	}
	
	
	
	
	@Bean
	CustomAuthenticationFilter tokenProcessingFilter() throws Exception {
		CustomAuthenticationFilter tokenProcessingFilter = new CustomAuthenticationFilter();
	  return tokenProcessingFilter;
	}

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.cors().and().csrf().disable()
		.addFilter(tokenProcessingFilter())
		.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/css/images/**","/fonts/**","/fav**.ico",  "/loadAllPartnerID", "/loginPage").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/loginPage"))
		.and()
		
//		.csrf()
//		.and()
		.formLogin()
		.loginPage("/loginPage").failureHandler(customAuthenticationFailureHandler)
		.permitAll().successForwardUrl("/home")
		.and().sessionManagement().sessionAuthenticationStrategy(concurrentSession())
		.and().addFilterBefore(concurrentSessionFilter(), ConcurrentSessionFilter.class);

	}
	
	@Bean
	public CompositeSessionAuthenticationStrategy concurrentSession() {

		ConcurrentSessionControlAuthenticationStrategy concurrentAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(getSessionRegistry());
		concurrentAuthenticationStrategy.setMaximumSessions(noOfConcurrentSesion);
		concurrentAuthenticationStrategy.setExceptionIfMaximumExceeded(errorAfterMaximumSession);
		concurrentAuthenticationStrategy.setExceptionIfMaximumExceeded(false);
		List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<SessionAuthenticationStrategy>();
		delegateStrategies.add(concurrentAuthenticationStrategy);
        delegateStrategies.add(new SessionFixationProtectionStrategy());
        delegateStrategies.add(new RegisterSessionAuthenticationStrategy(getSessionRegistry()));

        CompositeSessionAuthenticationStrategy authenticationStrategy =  new CompositeSessionAuthenticationStrategy(delegateStrategies);
        return authenticationStrategy;
    }

    @Bean
    ConcurrentSessionFilter concurrentSessionFilter() {
        /*CustomSessionInformationExpiredStrategy redirectStrategy = new CustomSessionInformationExpiredStrategy("/pub/multiplesessions.html");
        CustomConcurrentSessionFilter concurrentSessionFilter = new CustomConcurrentSessionFilter(getSessionRegistry(), redirectStrategy);
        return concurrentSessionFilter;*/
    	_log.debug("++[SecurityConfiguration.getConcurrentSessionFilter() method called]++");
		return new ConcurrentSessionFilter(getSessionRegistry(),"/loginPage");
    }
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		
		return new GrantedAuthorityDefaults(ApplicationConfig.PRIVILEGE);
	}
	
	@Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		_log.debug("++[SecurityConfiguration.httpSessionEventPublisher() method called]++");
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
	
	@Bean
    SessionRegistry getSessionRegistry() {
		_log.debug("++[SecurityConfiguration.sessionRegistry() method called]++");
        return new SessionRegistryImpl();
    }

}
