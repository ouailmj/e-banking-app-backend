package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.AccountRepository;
import com.example.ebankingspg.java.model.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService extends AbstractService<Long, AccountRepository, Account> {
    public AccountService(AccountRepository repository) {
        super(repository);
    }

    public Optional<Account> findByRib(String rib) {
        return repository.findByRib(rib);
    }
}
