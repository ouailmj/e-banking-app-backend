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

import com.example.ebankingspg.java.Repository.AgenceRepository;
import com.example.ebankingspg.java.Repository.TypeContratRepository;
import com.example.ebankingspg.java.Repository.UserRepository;
import com.example.ebankingspg.java.model.*;
@CrossOrigin()
@RestController
@RequestMapping({ "/typecontrats" })
public class TypeContratController {

   @Autowired
   private TypeContratRepository typecontrat;

   @GetMapping(produces = "application/json")
   public List<TypeContrat> firstPage() {
       List<TypeContrat> typecontrats = typecontrat.findAll();
       return typecontrats;
   }

   @PostMapping
   public TypeContrat create(@RequestBody TypeContrat typecontrats) {

       typecontrat.save(typecontrats);

       return typecontrats;
   }

}
