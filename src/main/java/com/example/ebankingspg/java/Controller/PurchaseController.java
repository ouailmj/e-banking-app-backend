package com.example.ebankingspg.java.Controller;


import com.example.ebankingspg.java.model.Account;
import com.example.ebankingspg.java.model.Client;
import com.example.ebankingspg.java.model.Operator;
import com.example.ebankingspg.java.model.Purchase;
import com.example.ebankingspg.java.model.dto.PhoneVerificationVerifyPurchaseDTO;
import com.example.ebankingspg.java.model.dto.PurchaseDTO;
import com.example.ebankingspg.java.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private PhoneVerificationService phoneVerificationService;

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    //public static final String API_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFLUJBTkstQVBQXzIiLCJleHAiOjE1Nzc3NDE3MDEsImNsaWVudF9pZCI6MX0.yJ0R24inxkq7xtJ91rGPT2smYMBk6e_4NjJ3Sirywdz4ULN1t2Ky4uYOG6LgX-W-xzDCw95A3bOxgYYCv9JYNg";

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity refill(@Valid @RequestBody PhoneVerificationVerifyPurchaseDTO dto) {
        try {
            phoneVerificationService.verify(dto.getCountryCode(),dto.getPhoneNumber(),dto.getToken());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("token invalid");
        }

        try {
            Account account = accountService.findByRib(dto.getPurchase().getRib()).orElseThrow(() -> new Exception("account not found"));
            if (account.getBalance() > dto.getPurchase().getAmount()) {
                Operator operator = operatorService.findByName(dto.getPurchase().getOperator());
                final String uri = operator.getHost() + "v1/operator/purchase/create";
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add(HEADER_STRING, TOKEN_PREFIX + operator.getApiKey());
                HttpEntity<PurchaseDTO> entity = new HttpEntity<PurchaseDTO>(dto.getPurchase(), headers);
                ResponseEntity responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Purchase.class);
                if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your balance is insufficient");
                } else {
                    account.setBalance(account.getBalance() - dto.getPurchase().getAmount());
                    accountService.update(account);
                    Client client = clientService.findByAccount(account);
                    Purchase purchase = Purchase.builder().amount(dto.getPurchase().getAmount()).phoneNumber(dto.getPhoneNumber())
                            .type(dto.getPurchase().getType()).client(client).build();
                    purchaseService.create(purchase);
                    return responseEntity;
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your balance is insufficient");
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("somethings is wrong !!");
        }
    }


    @GetMapping(value = "")
    public Page<Purchase> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "9") int size,
                                  @RequestParam(name = "sort", defaultValue = "createdDate") String sort) {
        return purchaseService.findAll(page, size, sort);
    }

}
