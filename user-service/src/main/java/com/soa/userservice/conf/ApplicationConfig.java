package com.soa.userservice.conf;


import com.soa.userservice.pojo.usersFromConfigServer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({usersFromConfigServer.class})
public class ApplicationConfig {
}
