package com.appln.securitydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

  @Autowired
   public void configure(AuthenticationManagerBuilder auth)
           throws Exception
   {
       auth.inMemoryAuthentication()
           .withUser("vithyaa").password("{noop}password").roles("USER").and()
           .withUser("demo").password("test").roles("admin");           
   }
 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("authorization");
        http.authorizeRequests()
        .antMatchers("/hello").hasRole("USER")
//        .antMatchers("/hello").hasRole("admin")
        .anyRequest()//get put post 
        .fullyAuthenticated() //every request must be authenticated
        .and().httpBasic();//basic security
        http.csrf().disable();//cross site request forgery  unnecessary popups
    }
}