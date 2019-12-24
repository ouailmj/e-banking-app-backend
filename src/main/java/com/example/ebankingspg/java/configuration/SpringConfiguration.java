package com.example.ebankingspg.java.configuration;

import com.authy.AuthyApiClient;
import com.example.ebankingspg.java.services.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

@Configuration
public class SpringConfiguration {

    @Autowired
    private Settings settings;

    @Bean
    public AuthyApiClient authyApiClient() {
        return new AuthyApiClient(settings.getAuthyId());
    }


}
