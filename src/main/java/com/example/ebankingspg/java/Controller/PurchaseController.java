package com.example.ebankingspg.java.Controller;


import com.example.ebankingspg.java.model.Account;
import com.example.ebankingspg.java.model.Client;
import com.example.ebankingspg.java.model.Purchase;
import com.example.ebankingspg.java.model.dto.PurchaseDTO;
import com.example.ebankingspg.java.services.AccountService;
import com.example.ebankingspg.java.services.ClientService;
import com.example.ebankingspg.java.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@RequestMapping("/purchase")
@CrossOrigin(origins = "*")
@RestController
public class PurchaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ClientService clientService;

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFLUJBTkstQVBQXzIiLCJleHAiOjE1Nzc3NDE3MDEsImNsaWVudF9pZCI6MX0.yJ0R24inxkq7xtJ91rGPT2smYMBk6e_4NjJ3Sirywdz4ULN1t2Ky4uYOG6LgX-W-xzDCw95A3bOxgYYCv9JYNg";

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Purchase> refill(@RequestBody PurchaseDTO purchaseDTO) {

        try {
            Account account = accountService.findByRib(purchaseDTO.getRib()).orElseThrow(() -> new Exception("account not found"));
            if (account.getBalance() > purchaseDTO.getAmount()) {
                final String uri = "http://localhost:9000//v1/operator/purchase/create";
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add(HEADER_STRING, TOKEN_PREFIX + API_KEY);
                HttpEntity<PurchaseDTO> entity = new HttpEntity<PurchaseDTO>(purchaseDTO, headers);
                ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Purchase.class);
                if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    account.setBalance(account.getBalance() - purchaseDTO.getAmount());
                    accountService.update(account);
                    Client client = clientService.findByAccount(account);
                    Purchase purchase = Purchase.builder().amount(purchaseDTO.getAmount()).phoneNumber(purchaseDTO
                            .getPhoneNumber()).type(purchaseDTO.getType()).client(client).build();
                    purchaseService.create(purchase);
                    return responseEntity;
                }
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "")
    public Page<Purchase> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "9") int size,
                                  @RequestParam(name = "sort", defaultValue = "createdDate") String sort) {
        return purchaseService.findAll(page, size, sort);
    }

}
