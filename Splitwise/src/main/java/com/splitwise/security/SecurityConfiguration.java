package com.splitwise.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@ComponentScan(value="com.splitwise")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static String REALM="MY_TEST_REALM";
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Started");
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username,password,enabled FROM customers where username = ?")
				.authoritiesByUsernameQuery("select username,role_name from customers join Roles on customers.user_id = Roles.user_id where username = ?");
		
		System.out.println("Ended");
	}
	// .hasRole("ADMIN")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/Splitwise/**").hasAnyRole("ADMIN","USER")
		.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
 	}
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
