package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.DeviseRepository;
import com.example.ebankingspg.java.model.Devise;
import org.springframework.stereotype.Service;


@Service
public class DeviseService  extends AbstractService<Long, DeviseRepository, Devise> {
    public DeviseService(DeviseRepository repository) {
        super(repository);
    }
}
