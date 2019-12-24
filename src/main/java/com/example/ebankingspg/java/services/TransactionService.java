package com.example.ebankingspg.java.services;

import com.example.ebankingspg.java.Repository.AccountRepository;
import com.example.ebankingspg.java.Repository.RoleRepository;
import com.example.ebankingspg.java.Repository.TransactionRepository;
import com.example.ebankingspg.java.model.Account;
import com.example.ebankingspg.java.model.Role;
import com.example.ebankingspg.java.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService extends AbstractService<Long, TransactionRepository, Transaction> {
    @Autowired
    private AccountRepository accountRepository;


    public TransactionService(TransactionRepository repository) {
        super(repository);
    }

    public boolean verifyByTransactionManager(Account accountsender, Account accounttarget, long amount) {
        List<Transaction> transaction = repository.findByAccountTarget(accounttarget);
        List<Transaction> transactionsender = repository.findByAccount(accountsender);
        double somme = amount;
        double revenu = 0;
        int nbrtransparjour = 0;
        for (Transaction tr : transactionsender) {
            LocalDate today = LocalDate.now();
            LocalDate d = LocalDate.of(tr.getCreatedDate().getYear(), tr.getCreatedDate().getMonth(), tr.getCreatedDate().getDayOfMonth());
            int diff = (int) ChronoUnit.DAYS.between(d, today);
            if (diff < 1) nbrtransparjour++;
        }
        for (Transaction tr : transaction) {
            LocalDate today = LocalDate.now();
            LocalDate d = LocalDate.of(tr.getCreatedDate().getYear(), tr.getCreatedDate().getMonth(), tr.getCreatedDate().getDayOfMonth());
            int diff = (int) ChronoUnit.DAYS.between(d, today);
            revenu = revenu + tr.getAmount();
            if (diff <= 30) {
                somme = somme + tr.getAmount();
            }
        }
        LocalDate d = LocalDate.of(accounttarget.getCreatedDate().getYear(), accounttarget.getCreatedDate().getMonth(), accounttarget.getCreatedDate().getDayOfMonth());
        LocalDate now = LocalDate.now();
        revenu = revenu / ChronoUnit.MONTHS.between(d, now);
        long resultat = (long) (somme / revenu);

        if (resultat > 1.5 || nbrtransparjour > 3) return false;
        return true;
    }

    public String transfer(Account account, Account recipient, double amount, String recipientRib) {
        //verification de recipient in bank or dehors?
        if (recipient == null) {
            //augmenter ammount
            amount = amount + amount * (account.getTypecontrat().getRate()) / 100;
        }
        //verification Solde
        String msg;

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            Transaction transaction;
            if (recipient == null) {
                transaction = Transaction.builder().account(account).recipientOutOfBank(recipientRib).amount(amount).active(true).build();
            } else {


                if (verifyByTransactionManager(account, recipient, (long) amount)) {
                    transaction = Transaction.builder().account(account).accountTarget(recipient).amount(amount).active(true).build();
                    recipient.setBalance(recipient.getBalance() + amount);
                } else {
                    transaction = Transaction.builder().account(account).accountTarget(recipient).amount(amount).active(false).build();
                    accountRepository.save(account);
                    repository.save(transaction);
                    return "blocked";
                }
                accountRepository.save(recipient);
            }
            accountRepository.save(account);
            repository.save(transaction);

        } else {
            return "error";
        }
        return "Your Transaction is successful";
    }
}
