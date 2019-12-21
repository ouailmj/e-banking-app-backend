package com.example.ebankingspg.java.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebankingspg.java.Repository.AgenceRepository;
import com.example.ebankingspg.java.Repository.UserRepository;
import com.example.ebankingspg.java.model.*;
import com.example.ebankingspg.java.request.Addagencerequest;
import com.example.ebankingspg.java.services.AgenceService;
@CrossOrigin("*")
@RestController
@Controller("/agence")
public class AgenceController {
/*
   @Autowired
   private AgenceRepository agencerep;

   @GetMapping(produces = "application/json")
   public List<Agency> firstPage() {
       List<Agency> agences = agencerep.findAll();
       return agences;
   }
@Autowired
AgenceService agenceservice;

   @GetMapping(produces = "application/json")
   @RequestMapping(value = "/admin/addagences",method = RequestMethod.POST)
   public ResponseEntity<?> addgestagence(@RequestBody Addagencerequest addagence) throws Exception { {
     Agency agence = new Agency();

     agence.setNom(addagence.getNom());
     agence.setNumtel(addagence.getPhone());
     agence.setAddress(addagence.getAddress());

      agenceservice.create(agence);

       return ResponseEntity.ok(addagence);
   }

   }*/
}
