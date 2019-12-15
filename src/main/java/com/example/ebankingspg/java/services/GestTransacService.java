package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.GesttransacRepository;
import com.example.ebankingspg.java.model.GestTransac;
import org.springframework.stereotype.Service;

@Service
public class GestTransacService extends AbstractService<Long, GesttransacRepository, GestTransac>{
    public GestTransacService(GesttransacRepository repository) {
        super(repository);
    }
}
