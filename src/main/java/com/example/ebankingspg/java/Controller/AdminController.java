package com.example.ebankingspg.java.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.ebankingspg.java.Repository.*;
import com.example.ebankingspg.java.request.UpdateClientRequest;
import com.example.ebankingspg.java.response.ChartResponse;
import com.example.ebankingspg.java.response.ClientResponse;
import com.example.ebankingspg.java.Repository.AgenceRepository;
import com.example.ebankingspg.java.Repository.ClientRepository;
import com.example.ebankingspg.java.Repository.CommissionRepository;
import com.example.ebankingspg.java.Repository.DeviseRepository;
import com.example.ebankingspg.java.Repository.RoleRepository;
import com.example.ebankingspg.java.response.StatistiqueResponse;
import com.example.ebankingspg.java.response.StringResponse;
import com.example.ebankingspg.java.services.GestClientService;
import com.example.ebankingspg.java.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.ebankingspg.java.model.*;

import javax.xml.ws.Response;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/admin" })
public class AdminController {

@Autowired
ClientRepository clientrep;

@Autowired
GestClientRepository gestclientrep;

@Autowired
  private GestClientService gestClientService;


@GetMapping(produces = "application/json")
public List<GestClient> firstPage() {
    List<GestClient> gestclients = gestClientService.findAll();
    return gestclients;
}
@Autowired
private RoleRepository rolerep;



@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;

@PostMapping
public GestClient create(@RequestBody GestClient gestcli) {

String pass = gestcli.getPassword();
String newpass = bCryptPasswordEncoder.encode(pass);
gestcli.setPassword(newpass);
Set<Role> roles1 = new HashSet<Role>();
roles1.add(rolerep.findByRole("ROLE_CLIENT_MANAGER"));
gestcli.setRoles(roles1);
    gestClientService.create(gestcli);
    return gestcli;
}

}
