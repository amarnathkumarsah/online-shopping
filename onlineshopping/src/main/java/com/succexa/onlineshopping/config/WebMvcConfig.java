package com.succexa.onlineshopping.config;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.executor.FlowExecutorImpl;

/*import com.succexa.onlineshopping.config.filter.MyFilter;
*/

/*@Import({ AppConfigOthers.class })
@ImportResource({
     "classpath:/config/spring-web-servlet.xml", 
     "classpath:/config/database-config.xml"
})*/
@MultipartConfig(
		//location="/tmp", 
		fileSizeThreshold= 1024*1024, //1 MB
		maxFileSize= 1024*1024*5, // 4 MB
		maxRequestSize= 1024*1024*5*5)// 25 MB
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.succexa.onlineshopping,com.succexa.shoppingbackend"})
public class WebMvcConfig implements WebMvcConfigurer {

	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
   @Bean("resolver")
   public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }

   @Bean
   public MessageSource messageSource() {
      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasename("messages");
      return source;
   }

   @Override
   public Validator getValidator() {
      LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
      validator.setValidationMessageSource(messageSource());
      return validator;
   }
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   System.out.println("this is from webmvcconfig");
       registry.addResourceHandler("/resources/**").addResourceLocations("/assets/");
       
   }
   
   @Bean(name = "multipartResolver")
   public StandardServletMultipartResolver multipartResolver() {
	   
       return new StandardServletMultipartResolver();
   }
  
 /*  @Bean
   public MyFilter myFilter(@Value("${someParameter}") String someParameter) {
       return new MyFilter(someParameter);
   }
   */
   
/*   @Bean(name = "multipartResolver")
   public StandardServletMultipartResolver createMultipartResolver() {
	   StandardServletMultipartResolver resolver=new StandardServletMultipartResolver();
	   
	   resolver.
       return resolver;
   }*/
   
   
 /*  @Bean
   public CommonsMultipartResolver multipartResolver() {

       CommonsMultipartResolver cmr = new CommonsMultipartResolver();
       cmr.setMaxUploadSize(maxUploadSizeInMb * 2);
       cmr.setMaxUploadSizePerFile(maxUploadSizeInMb); //bytes
       return cmr;

   }*/
   
 /*  @Bean(name = "multipartResolver")
   public CommonsMultipartResolver multipartResolver() {
       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
       multipartResolver.setMaxUploadSize(1000000);
       return multipartResolver;
   }*/
   
   
  
/*   @Bean(name = "multipartResolver")
   public CommonsMultipartResolver createMultipartResolver() {
       CommonsMultipartResolver resolver=new CommonsMultipartResolver();
       resolver.setDefaultEncoding("utf-8");
       return resolver;
   }*/
   
}
