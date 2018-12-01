package com.succexa.onlineshopping.security.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.succexa.shoppingbackend.config.HibernateConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
@Autowired
HibernateConfig hibernateConfig;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//	String password = passwordEncoder().encode("password");
		
		auth
		.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(hibernateConfig.getDataSource())
		.usersByUsernameQuery("select EMAIL ,PASSWORD ,ENABLED  from USER_DETAIL where EMAIL = ? ")
		.authoritiesByUsernameQuery("select EMAIL,ROLE from USER_DETAIL where EMAIL = ?");
		
		
		//  auth.inMemoryAuthentication().withUser("amar_dev").password(passwordEncoder().encode("password")).roles("USER,ADMIN");
		// auth.inMemoryAuthentication().withUser("amar_dev").password(passwordEncoder().encode("password")).roles("USER,ADMIN");
    }

	@Override
	public void configure(WebSecurity security) throws Exception{
		security.ignoring().regexMatchers("/resources/.*");
	}
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 System.out.println("i am in configure");
		    http
		    	.authorizeRequests()
		    	
		    	/*Only admin access*/
		    	.antMatchers("/manage/**").hasAuthority("ADMIN")
		    	
		    	/*Only user access*/
		    	.antMatchers("/cart/**").hasAuthority("USER")
		    
		    	/*Rest of the world*/
		    	.antMatchers("/**").permitAll()
		    	
		    	.and()
		    	
		    	.formLogin().loginPage("/login")
		    	
		    	.and()
		    	
		    	.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//		    	.exceptionHandling().accessDeniedPage("/access-denied")
//		    	
		    	/*.and()
		    	
		    	.logout();*/
		    	
		    
		    
		        //.authorizeRequests()
		        //.antMatchers("/resources/**").hasRole("SUPPLIER")
		       // .antMatchers("/resources/**", "/signup", "/about").hasAuthority("SUPPLIER")//.permitAll()       
//		        .antMatchers("/resources/**", "/signup", "/about").permitAll()        
//	            .antMatchers("/admin/**").hasRole("SUPPLIER")                                      
//	            .antMatchers("/db/**").hasAnyAuthority("USER,SUPPLIER")           
//	            .anyRequest().authenticated()  
//		        .and()
//		        .formLogin().loginPage("/login").permitAll(); 
		        //.formLogin().loginPage("/login").permitAll();  default working fine
		          //.and()
		        //.httpBasic(); for basic
		}

	 @Bean
	 public AccessDeniedHandler accessDeniedHandler(){
	     return new CustomAccessDeniedHandler();
	 }
	 
	
	/* If we dont have any endcoding password machanism*/
	//@SuppressWarnings("deprecation")
	//@Bean
	//public static NoOpPasswordEncoder passwordEncoder() {
	//return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	//}
		
	 
	 @Bean(name="passwordEncoder")
	 public PasswordEncoder passwordEncoder() {
		 System.out.println("i am creating bean with the password encoder bean");
	     return new BCryptPasswordEncoder();
	 }
	 
	 /*another password encoding machanism*/
//	 @Bean
//     public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	 }
	 
	 
/*@Bean
public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
    return manager;
}*/

	//my custom spring config
/*	 @Override
	    protected void configure(HttpSecurity http) throws Exception{
		 http.authorizeRequests().antMatchers("/**").hasRole("USER")
		 .and()
		 .formLogin()
		 .and()
		 .logout()
		 ;*/
		 
//	     http.authorizeRequests()
//	                .antMatchers("/list").access("hasRole('USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//                .antMatchers("/new-user", "/delete-user/*").access("hasRole('ADMIN')")
//	                .antMatchers("/edit-user/*").access("hasRole('ADMIN') or hasRole('USER')")
//	                .antMatchers("/**").hasAuthority("USER")
//	              .and()
//	                .formLogin()
//	                    .loginPage("/login.jsp")
//	                    .loginProcessingUrl("/login")
//	                    .usernameParameter("ssoid")
//	                    .passwordParameter("password")
//	                    .defaultSuccessUrl("/home")
//	                .and()
//	                    .logout()
//	                    .logoutUrl("/logout")
//	                    .logoutSuccessUrl("/logout")
//	                .and()
//	                .rememberMe()
//	                    .rememberMeParameter("remeber-me")
//	                    					//  .tokenRepository(persistentTokenRepository)
//	                    .tokenValiditySeconds(46800)
//	            .and().csrf()
//	                .and().exceptionHandling().accessDeniedPage("/access_denied");

//	 }
	 
	


/*The above lines of code is similar to the folling lines of code:->*/
/*		<http>
	    <intercept-url pattern="/**" access="authenticated"/>
	    <form-login />
	    <http-basic />
	</http>
	*/
		
	 
	 /* @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
	        return manager;
	    }*/
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.regexMatchers("/USER/.*").hasRole("USER")
				.regexMatchers("/ADMIN/.*").access("hasRole('USER') and principal.name='James Bond'")
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().requiresChannel().anyRequest().requiresSecure();
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.formLogin().loginPage("/login").permitAll();
		
		http.logout().logoutUrl("/customlogout");
				
	}*/
	

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("i am in configureGlobal");
		auth.inMemoryAuthentication()
        .withUser("user").password("user").roles("USER")
        .and().withUser("admin").password("admin").roles("USER", "ADMIN")
        .and().withUser("user1@example.com").password("user1").roles("USER")
        .and().withUser("admin1@example.com").password("admin1").roles("USER", "ADMIN")
;
	}*/
	

	/* @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	          .antMatchers("/private/**")
	          .authenticated()
	          .antMatchers("/public/**")
	          .permitAll()
	          .and()
	          .httpBasic();
	    }*/
	 
	/* @Bean
	 public PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }*/
	 
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("i am in configure");
	  http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
		.and().formLogin();
		
	}*/
}
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
@Qualifier("customUserDetailsService")
UserDetailsService userDetailsService;

@Autowired
PersistentTokenRepository persistentTokenRepository;

@Override
protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
            .antMatchers("/","/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
            .antMatchers("/new-user", "/delete-user/*").access("hasRole('ADMIN')")
            .antMatchers("/edit-user/*").access("hasRole('ADMIN') or hasRole('USER')")
            .and()
            .formLogin()
                .loginPage("/login.jsp")
                .loginProcessingUrl("/login")
                .usernameParameter("ssoid")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
            .and()
                .logout()
                .logoutUrl("/logout")	
                .logoutSuccessUrl("/logout")
            .and()
            .rememberMe()
                .rememberMeParameter("remeber-me")
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(46800)
            .and().csrf()
            .and().exceptionHandling().accessDeniedPage("/access_denied");
}

@Autowired
public void configureGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception{
    builder.userDetailsService(userDetailsService);
    builder.authenticationProvider(authenticationProvider());
}

@Bean
public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
}

@Bean
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}

@Bean
public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
    PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
            "remember-me", userDetailsService, persistentTokenRepository);
    return tokenBasedservice;
}

@Bean
public AuthenticationTrustResolver getAuthenticationTrustResolver() {
    return new AuthenticationTrustResolverImpl();
}

}*/

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("i am in configure");
        auth.inMemoryAuthentication()
          .withUser("user21").password(passwordEncoder().encode("user12Pass")).roles("USER")
          .and()
          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
          .and()
          .withUser("admin2").password(passwordEncoder().encode("admin2Pass")).roles("ADMIN");
    }
 
    */
    
   /* @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	System.out.println("i am in configure(final HttpSecurity http) ");
    	http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/anonymous*").anonymous()
          .antMatchers("/login*").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.html")
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/homepage.html", true)
          //.failureUrl("/login.html?error=true")
          .failureHandler(authenticationFailureHandler())
          .and()
          .logout()
          .logoutUrl("/perform_logout")
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler(logoutSuccessHandler());
    }*/

/*     @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/","/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/new-user", "/delete-user/*").access("hasRole('ADMIN')")
                .antMatchers("/edit-user/*").access("hasRole('ADMIN') or hasRole('USER')")
                .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/login")
                    .usernameParameter("ssoid")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logout")
                .and()
                .rememberMe()
                    .rememberMeParameter("remeber-me")
                  //  .tokenRepository(persistentTokenRepository)
                    .tokenValiditySeconds(46800)
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }*/

    
/*    private LogoutSuccessHandler logoutSuccessHandler() {
    	System.out.println("i am in logoutSuccessHandler");
    	return null;//new ForwardLogoutSuccessHandler("/google.com");
		
	}

	private AuthenticationFailureHandler authenticationFailureHandler() {
		System.out.println("i am in authenticationFailureHandler");
		return null;
	
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
		System.out.println("i am in passwordEncoder");
        return new BCryptPasswordEncoder();
    }
    
}
*/



/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.regexMatchers("/USER/.*").hasRole("USER")
				.regexMatchers("/ADMIN/.*").access("hasRole('USER') and principal.name='James Bond'")
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().requiresChannel().anyRequest().requiresSecure();
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.formLogin().loginPage("/login").permitAll();
		
		http.logout().logoutUrl("/customlogout");
				
	}
	
	@Override
	public void configure(WebSecurity security) throws Exception{
		security.ignoring().regexMatchers("/resources/.*");
	}
	
}
*/


/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.regexMatchers("/USER/.*").hasRole("USER")
				.regexMatchers("/ADMIN/.*").access("hasRole('USER') and principal.name='James Bond'")
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().requiresChannel().anyRequest().requiresSecure();
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.formLogin().loginPage("/login").permitAll();
		
		http.logout().logoutUrl("/customlogout");
				
	}
	
	@Override
	public void configure(WebSecurity security) throws Exception{
		security.ignoring().regexMatchers("/resources/.*");
	}
	
}*/
