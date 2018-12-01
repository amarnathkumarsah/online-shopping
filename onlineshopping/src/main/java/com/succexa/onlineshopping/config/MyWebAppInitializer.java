package com.succexa.onlineshopping.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.succexa.onlineshopping.util.FileUploadUtility;
import com.succexa.shoppingbackend.config.HibernateConfig;
/*
@MultipartConfig(
location="/tmp", 
fileSizeThreshold= 1024*1024, //1 MB
maxFileSize= 1024*1024*5, // 4 MB
maxRequestSize= 1024*1024*5*5)// 25 MB
*/
@ComponentScan(basePackages = {"com.succexa.onlineshopping,com.succexa.shoppingbackend"})
public class MyWebAppInitializer implements WebApplicationInitializer  {
//for versions earlier than Spring 5, we have to use the WebMvcConfigurerAdapter class instead of the interface.

	private int maxFileSize = 2 * 1024 * 1024; 
    private int maxRequestSize = 4 * 1024 * 1024; 
    private int fileSizeThreshold = 1 * 1024 * 1024; 
	
    @Override
	public void onStartup(ServletContext container) throws ServletException {
System.out.println("i am in onstartup method");
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		//rootContext.register(HibernateConfig.class);
		//rootContext.register(WebFlowConfig.class);
		
		rootContext.scan("com.succexa");
		
		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		ServletRegistration.Dynamic dispatcherContext =  
									container.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
		dispatcherContext.setLoadOnStartup(1);
		dispatcherContext.addMapping("/");
		dispatcherContext.setInitParameter("throwExceptionIfNoHandlerFound", "true");
				System.out.println("i am in web app intializer");
				
		dispatcherContext.setMultipartConfig(new  MultipartConfigElement("/assets/images/",
		                       maxFileSize,
		                       maxRequestSize,
		                       fileSizeThreshold));
				
		
		// Create the dispatcher servlet's Spring application context
	//	AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
	//	dispatcherContext.register(WebMvcConfig.class);
		
		// Register and map the dispatcher servlet
	/*	ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
		                                           new DispatcherServlet(dispatcherContext));*/
/*		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		
		dispatcher.setMultipartConfig(new  MultipartConfigElement("/assets/images/",
                       maxFileSize,
                       maxRequestSize,
                       fileSizeThreshold));*/
		/*
		 * FilterRegistration.Dynamic multipartFilter =
		 * container.addFilter("multipartFilter", FileUploadUtility.class);
		 * multipartFilter.addMappingForUrlPatterns(null, true, "/*");
		 */
		
		//Using commons-fileupload
		/*MultipartConfigElement multipartConfigElement = 
				new MultipartConfigElement();
		multipartConfigElement.getLocation();
		multipartConfigElement.getMaxFileSize()
		multipartConfigElement.getMaxRequestSize()
		multipartConfigElement.getFileSizeThreshold()*/
		
		

	}
  
}