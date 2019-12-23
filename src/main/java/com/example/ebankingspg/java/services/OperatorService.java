package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.OperatorRepository;
import com.example.ebankingspg.java.model.Operator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService extends AbstractService<Long, OperatorRepository, Operator> {
    public OperatorService(OperatorRepository repository) {
        super(repository);
    }

    public List<Operator> findAll() {
        return repository.findAll();
    }

    public Operator findByName(String name) {
        return repository.findByName(name);
    }
}
