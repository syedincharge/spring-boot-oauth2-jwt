package com.rizvi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//import javax.xml.bind.annotation.XmlRegistry;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class SpringBootOauth2JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2JwtApplication.class, args);
	}

}
