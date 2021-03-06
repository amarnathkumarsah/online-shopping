package com.succexa.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
@Import({WebAppConfiguration.class})
@PropertySource("classpath:/" + prop.properties)
@ImportResource({"classpath:/qry.xml"}
@ComponentScan(basePackages="com.succexa.onlineshopping,com.succexa.shoppingbackend")

*/
@ComponentScan(basePackages="com.succexa.shoppingbackend")
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	//change the below based on the DBMS
	private final static String DATABASE_URL="jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER="org.h2.Driver";
	private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME="sa";
	private final static String DATABASE_PASSWORD="";
	
	@Bean
	public DataSource getDataSource(){
	// Providing the db connection information
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
	
		return dataSource;
	}
	
	//SessionFactory bean will be available
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		
		LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		factoryBuilder.addProperties(getHibernateProperties());
		factoryBuilder.scanPackages("com.succexa.shoppingbackend");
	
		return factoryBuilder.buildSessionFactory();
	}

					
	// All the Hibernate Properties will be returned in this method
	private Properties getHibernateProperties() {
		
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect",DATABASE_DIALECT);
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.format_sql","true");
		properties.put("hibernate.hbm2ddl.auto","update");
		
		return properties;
		
	}
	
	//Transaction manager bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager; 
	}
	
}