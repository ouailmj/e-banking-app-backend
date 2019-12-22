package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.PurchaseRepository;
import com.example.ebankingspg.java.model.Purchase;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService extends AbstractService<Long, PurchaseRepository, Purchase> {
    public PurchaseService(PurchaseRepository repository) {
        super(repository);
    }
}
