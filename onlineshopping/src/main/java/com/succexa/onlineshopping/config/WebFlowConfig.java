package com.succexa.onlineshopping.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.ViewFactoryCreator;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
@ComponentScan(basePackages="com.succexa")
@Configuration
@EnableTransactionManagement
public class WebFlowConfig extends AbstractFlowConfiguration {
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
		        .setBasePath("/WEB-INF/views/flows")
		        .addFlowLocationPattern("/**/*-flow.xml")
		        .build();
	}
	
	//Entry Point for the flow
	@Bean
	public FlowExecutor flowExecutor() {
	    return getFlowExecutorBuilder(flowRegistry()).build();
	}
	
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter( ) {
	      FlowHandlerAdapter flowAdapter =  new FlowHandlerAdapter();
	      flowAdapter.setFlowExecutor(flowExecutor());
	      return flowAdapter;
	}
	
	@Bean
	public  FlowBuilderServices flowBuilderServices() {
		//FlowBuilderServices flowbuilderServices =  new FlowBuilderServices();
		//flowbuilderServices.setViewFactoryCreator(viewFactoryCreator());
		
		return getFlowBuilderServicesBuilder()
				.setValidator(getValidator())
				.setViewFactoryCreator(viewFactoryCreator()) //for validation purpose
				.build();
		//return getFlowBuilderServicesBuilder().build();
		//return new FlowBuilderServicesBuilder();
	}

	  //Validator Bean 
	  @Bean
	   public Validator getValidator() {
	      LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	      return validator;
	   }
	   
	
	/*@Bean
	public FlowBuilderServices flowBuilderServices() {
	    return getFlowBuilderServicesBuilder()
	            .setConversionService(conversionService())
	            .setExpressionParser(expressionParser)
	            .setViewFactoryCreator(mvcViewFactoryCreator())
	            .build();
	}*/

	
	@Autowired
	WebMvcConfig webMvcConfig;
	
	@Bean
	public ViewFactoryCreator viewFactoryCreator() {
		MvcViewFactoryCreator creator = new MvcViewFactoryCreator();
		List<ViewResolver> viewResolvers = new ArrayList<>() ;
		viewResolvers.add(webMvcConfig.resolver());
		creator.setViewResolvers(viewResolvers );
		//creator.setDefaultViewSuffix(".jsp");
		return creator;
	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping( ) {
		FlowHandlerMapping flowMapping =  new FlowHandlerMapping();
	    flowMapping.setFlowRegistry(flowRegistry());
	    flowMapping.setOrder(-1); //for max preference by this id(register)
	    return flowMapping;
	}
		
}
