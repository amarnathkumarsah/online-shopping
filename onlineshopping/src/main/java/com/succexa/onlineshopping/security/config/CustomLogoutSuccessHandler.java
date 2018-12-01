package com.succexa.onlineshopping.security.config;

/*import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		setDefaultTargetUrl("/login");
		logger.info("Remote Address is :"+request.getRemoteAddr());
		logger.info("Remote Host is :"+request.getRemoteHost());
		logger.info("Browser info"+request.getHeader("User-Agent"));
		super.onLogoutSuccess(request, response, authentication);
	}
	

}
*/