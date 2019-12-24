package com.example.ebankingspg.java.services;

import org.springframework.stereotype.Service;

@Service
public class Settings {

    public String getAuthyId(){
        return "XPlJ4u5s1nwQotrARbiMoHpSnAiWrO1f";
        //return System.getenv("ACCOUNT_SECURITY_API_KEY");
    }
}
