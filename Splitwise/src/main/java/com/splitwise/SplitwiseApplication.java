package com.splitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.splitwise.security.SecurityConfiguration;

@SpringBootApplication
@EnableTransactionManagement
//@Import({ SecurityConfiguration.class })
public class SplitwiseApplication {
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	//org.hibernate.dialect.MySQL5Dialect
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/splitwise");
	driverManagerDataSource.setUsername("root");
	driverManagerDataSource.setPassword("hbstudent");
	return driverManagerDataSource;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}
}
