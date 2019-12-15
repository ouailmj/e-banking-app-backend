package com.example.ebankingspg.java.Controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebankingspg.java.Repository.GestclientRepository;
import com.example.ebankingspg.java.Repository.GesttransacRepository;
import com.example.ebankingspg.java.Repository.UserRepository;
import com.example.ebankingspg.java.model.*;
@CrossOrigin()
@RestController
@RequestMapping({ "/gestclients" })
public class GestclientController {
   @Autowired
   private GestclientRepository gestclientrep;


   @GetMapping(produces = "application/json")
   public List<GestClient> firstPage() {
       List<GestClient> gestclients = gestclientrep.findAll();
       return gestclients;
   }


   @PostMapping
   public GestClient create(@RequestBody GestClient gestcli) {

       gestclientrep.save(gestcli);

       return gestcli;
   }


}
