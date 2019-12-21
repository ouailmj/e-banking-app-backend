package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.CommissionRepository;
import com.example.ebankingspg.java.Repository.DeviseRepository;
import com.example.ebankingspg.java.model.Commission;
import com.example.ebankingspg.java.model.Devise;

import org.springframework.stereotype.Service;

@Service
public class CommissionService extends AbstractService<Long, CommissionRepository, Commission>{
    public CommissionService(CommissionRepository repository) {
        super(repository);
    }
}
