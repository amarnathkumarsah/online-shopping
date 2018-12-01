//package com.succexa.onlineshopping.config;
//
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import com.succexa.shoppingbackend.config.HibernateConfig;
//
//public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	private AnnotationConfigWebApplicationContext a = null;
//   @Override
//   protected Class<?>[] getRootConfigClasses() {
//	  return new Class[] { HibernateConfig.class };
//   }
//
//   @Override
//   protected Class<?>[] getServletConfigClasses() {
//      return new Class[] { WebMvcConfig.class };
//   }
//  
//   @Override
//   protected String[] getServletMappings() {
//      return new String[] { "/" };
//   }
//}