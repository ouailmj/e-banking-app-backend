package com.example.ebankingspg.java.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.example.ebankingspg.java.Repository.*;
import com.example.ebankingspg.java.model.dto.PhoneVerificationVerifyTransactionDTO;
import com.example.ebankingspg.java.request.CreateRecipientRequest;
import com.example.ebankingspg.java.request.CreateTransactionRequest;
import com.example.ebankingspg.java.response.AccountResponse;
import com.example.ebankingspg.java.response.StringResponse;
import com.example.ebankingspg.java.response.TransactionDetailsResponse;
import com.example.ebankingspg.java.services.PhoneVerificationService;
import com.example.ebankingspg.java.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ebankingspg.java.model.*;
@CrossOrigin(origins = "*")
@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private PhoneVerificationService phoneVerificationService;


    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/transaction/createTransaction",method = RequestMethod.POST)
    public ResponseEntity<?> createTransaction(@Valid @RequestBody PhoneVerificationVerifyTransactionDTO dto) throws Exception {

        /*try {
            phoneVerificationService.verify(dto.getCountryCode(),dto.getPhoneNumber(),dto.getToken());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("token invalid");
        }*/

        //emetteur?
        Optional<Account> account = accountRepository.findByRib(dto.getTransaction().getAccount());
        account.orElseThrow(()->new Exception("User not found"));
        Account account1 = account.get();
        //recepteur?
        Optional<Account> recipient = accountRepository.findByRib(dto.getTransaction().getRecipient());
        String str;
        if(!recipient.isPresent()){
            str = transactionService.transfer(account1,null,dto.getTransaction().getAmount(),dto.getTransaction().getRecipient());
        }else{
            Account recipient1 = recipient.get();
            str = transactionService.transfer(account1,recipient1,dto.getTransaction().getAmount(),dto.getTransaction().getRecipient());
        }
        if(str.equals("error")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("not enough money in your account!!");
        } else if(str.equals("blocked")){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Your Transaction is sent but blocked");
        }else{
            return ResponseEntity.ok(new StringResponse(str));
        }
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/badTransactions",method = RequestMethod.GET)
    public ResponseEntity<?> getBadTransactions() throws Exception {
        List<Transaction> transactions = transactionRepository.findByActiveIsFalse();
        return ResponseEntity.ok(transactions);
    }

    //meryem
    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/transaction_details", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionDetails(@RequestParam String id) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(Long.parseLong(id));
        transaction.orElseThrow(() -> new Exception("transaction not found"));
        Transaction transaction1=transaction.get();
        String recipientRib;
        if(transaction1.getAccountTarget()==null){
            recipientRib=transaction1.getRecipientOutOfBank();
        }else{
            recipientRib=transaction1.getAccountTarget().getRib();
        }
        Account accountTarget = transaction1.getAccountTarget();
        if(accountTarget == null){
            return ResponseEntity.ok(new TransactionDetailsResponse(transaction1,transaction1.getAccount().getRib(),
                    recipientRib ,null));
        }else{
            return ResponseEntity.ok(new TransactionDetailsResponse(transaction1,transaction1.getAccount().getRib(),
                    recipientRib ,transaction1.getAccountTarget().getClient()));
        }
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/transaction_manager/valide_transaction",method = RequestMethod.POST)
    public ResponseEntity<?> validateTransaction(@RequestBody long id) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        Transaction transaction1=transaction.get();
        transaction1.setActive(true);
        Account account = transaction1.getAccountTarget();
        account.setBalance(account.getBalance()+transaction1.getAmount());
        accountRepository.save(account);
        transactionRepository.save(transaction1);
        return ResponseEntity.ok(new StringResponse("transaction validated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/transaction_manager/remove_transaction",method = RequestMethod.POST)
    public ResponseEntity<?> removeTransaction(@RequestBody long id) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        Transaction transaction1=transaction.get();
        //return money lkhona lmsskin
        Account account =transaction1.getAccount();
        account.setBalance(account.getBalance()+transaction1.getAmount());
        accountRepository.save(account);
        transactionRepository.delete(transaction1);
        return ResponseEntity.ok(new StringResponse("Transaction removed successfully"));
    }
}
