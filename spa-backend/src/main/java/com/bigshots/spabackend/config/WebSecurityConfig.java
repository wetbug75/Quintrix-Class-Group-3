package com.bigshots.spabackend.config;
import org.springframework.http.HttpMethod;

import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.azure.cosmos.implementation.guava25.collect.ImmutableList;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
   /*
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("Tryna hack this thing");
    	
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from users where username=?")
            .authoritiesByUsernameQuery("select username, role from users where username=?")
        ;
        System.out.println("this working?");
    }
 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//antiMatchers for the route you would like to protect
        http.cors().and().csrf().
        disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();

     /*   http.cors()
        		.and()
        	.authorizeRequests()
        		//.antMatchers("/newJoke").authenticated()
	            .anyRequest().permitAll()
	            .and()
            .formLogin()
            	.permitAll()
            	.and()
            .logout()
            	.permitAll()
            	.and()
            .csrf().disable(); //csrf protects from PUT requests*/
    }
  

} 