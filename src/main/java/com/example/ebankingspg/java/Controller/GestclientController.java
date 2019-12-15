package com.example.ebankingspg.java.Controller;

import java.util.List;

import com.example.ebankingspg.java.services.GestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebankingspg.java.model.*;

@CrossOrigin()
@RestController
@RequestMapping({"/gestclients"})
public class GestclientController {

    @Autowired
    private GestClientService gestClientService;


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


}
