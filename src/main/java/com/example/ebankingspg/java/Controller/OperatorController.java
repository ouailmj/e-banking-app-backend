package com.example.ebankingspg.java.Controller;

import com.example.ebankingspg.java.model.Operator;
import com.example.ebankingspg.java.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.ebankingspg.java.Controller.PurchaseController.*;
import static com.example.ebankingspg.java.DataInitializer.API_KEY;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/operators")
public class OperatorController {

    @Autowired
    private OperatorService service;

    @GetMapping(value = "")
    public List<Operator> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/purchase-types")
    public ResponseEntity<String> getPurchaseType() {

        final String uri = "http://localhost:9000//v1/operator/purchase-type";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HEADER_STRING, TOKEN_PREFIX + API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    }

}
