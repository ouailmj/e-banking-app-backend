package com.example.ebankingspg.java.Controller;

import java.util.List;
import java.util.Optional;

import com.example.ebankingspg.java.Repository.AgenceRepository;
import com.example.ebankingspg.java.Repository.ClientRepository;
import com.example.ebankingspg.java.Repository.CommissionRepository;
import com.example.ebankingspg.java.Repository.DeviseRepository;
import com.example.ebankingspg.java.response.StatistiqueResponse;
import com.example.ebankingspg.java.services.GestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ebankingspg.java.model.*;


@CrossOrigin(origins = "*")
@RestController
public class GestClientController {

    @Autowired
    private GestClientService gestClientService;

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DeviseRepository deviseRepository;

    @Autowired
    private CommissionRepository commissionRepository;

    @GetMapping(produces = "application/json")
    public List<GestClient> firstPage() {
        List<GestClient> gestclients = gestClientService.findAll();
        return gestclients;
    }


    @PostMapping
    public GestClient create(@RequestBody GestClient gestcli) {
        gestClientService.create(gestcli);
        return gestcli;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/dashboard")
    public ResponseEntity<?> createAuthenticationToken() throws Exception {
        long totalAgences = agenceRepository.count();
        long totalClient = clientRepository.count();
        Optional<Devise> tauxDeChange = deviseRepository.findByName("DHS");
        tauxDeChange.orElseThrow(()->new Exception("Taux de change not found"));
        Devise devise = tauxDeChange.get();
        double exchangeRate = devise.getExchangeRate();
        Commission commission = commissionRepository.findById(1);
        double transfert = (long) commission.getTransfert();
        return ResponseEntity.ok(new StatistiqueResponse(totalClient,totalAgences,exchangeRate,transfert));
    }


}
