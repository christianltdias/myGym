package com.exercisetracker.configuration;

import com.exercisetracker.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.exercisetracker.services.SmtpEmailService;
import org.springframework.context.annotation.Configuration;
import com.exercisetracker.services.EmailService;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws Exception {

        if (!strategy.equals("create"))
            return false;
                
        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}