package com.example.ebankingspg.java.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebankingspg.java.Repository.DeviseRepository;
import com.example.ebankingspg.java.model.*;
import com.example.ebankingspg.java.request.AdddeviseRequest;
import com.example.ebankingspg.java.services.AgenceService;
import com.example.ebankingspg.java.services.DeviseService;

@CrossOrigin("*")
@RestController
@Controller("/devise")
public class DeviseController {
/*
  @Autowired
  DeviseRepository devrep;


  @GetMapping(produces = "application/json")
  public List<Devise> firstPage() {
      List<Devise> devises = devrep.findAll();
      return devises;
  }
*/

}

