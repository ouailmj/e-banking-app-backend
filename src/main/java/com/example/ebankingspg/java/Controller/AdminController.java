package com.example.ebankingspg.java.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.ebankingspg.java.Repository.*;
import com.example.ebankingspg.java.request.Addagencerequest;
import com.example.ebankingspg.java.request.Addcommissionrequest;
import com.example.ebankingspg.java.request.AdddeviseRequest;
import com.example.ebankingspg.java.request.Addgestclientrequest;
import com.example.ebankingspg.java.request.Addgesttransacrequest;
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
import com.example.ebankingspg.java.services.AgenceService;
import com.example.ebankingspg.java.services.CommissionService;
import com.example.ebankingspg.java.services.DeviseService;
import com.example.ebankingspg.java.services.GestClientService;
import com.example.ebankingspg.java.services.GestTransacService;
import com.example.ebankingspg.java.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.ebankingspg.java.model.*;

import javax.xml.ws.Response;

@CrossOrigin(origins = "*")
@RestController
@Controller("/admin")
public class AdminController {

@Autowired
ClientRepository clientrep;

@Autowired
GestClientRepository gestclientrep;

@Autowired
  private GestClientService gestClientService;


@GetMapping(produces = "application/json")
@RequestMapping(value = "/admin/getclient")
public List<GestClient> firstPage() {
    List<GestClient> gestclients = gestClientService.findAll();
    return gestclients;
}
@Autowired
private RoleRepository rolerep;



@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;



@GetMapping(produces = "application/json")
@RequestMapping(value = "/admin/addgestclient",method = RequestMethod.POST)
public ResponseEntity<?> addgestclient(@RequestBody Addgestclientrequest addgestclient) throws Exception { {
  //Role role = rolerep.findByRole("ROLE_CLIENT_MANAGER");
  GestClient gestcli = new GestClient();
  gestcli.setEmail(addgestclient.getEmail());
  gestcli.setFirstname(addgestclient.getFirstname());
  gestcli.setLastname(addgestclient.getLastname());
  gestcli.setIsValid(false);
  gestcli.setNumtel(addgestclient.getPhone());
  gestcli.setAdress(addgestclient.getAddress());
String pass = addgestclient.getPassword();
String newpass = bCryptPasswordEncoder.encode(pass);
gestcli.setPassword(newpass);
Set <Role> set = new HashSet<Role>();
        //set.add(role);
       // gestcli.setRoles(set);

    gestClientService.create(gestcli);
    return ResponseEntity.ok(addgestclient);
}

}
@Autowired

GestTransacService gesttran;



@GetMapping(produces = "application/json")
@RequestMapping(value = "/admin/addgesttransac",method = RequestMethod.POST)
public ResponseEntity<?> addgesttransac(@RequestBody Addgesttransacrequest addgesttran) throws Exception { {
  //Role role = rolerep.findByRole("ROLE_TRANSACTION_MANAGER");
  GestTransac gestcli = new GestTransac();
  gestcli.setEmail(addgesttran.getEmail());
  gestcli.setFirstname(addgesttran.getFirstname());
  gestcli.setLastname(addgesttran.getLastname());
  gestcli.setIsValid(false);
  gestcli.setNumtel(addgesttran.getPhone());
  gestcli.setAdress(addgesttran.getAddress());
String pass = addgesttran.getPassword();
String newpass = bCryptPasswordEncoder.encode(pass);
gestcli.setPassword(newpass);
Set <Role> set = new HashSet<Role>();
        //set.add(role);
       // gestcli.setRoles(set);

    gesttran.create(gestcli);
    return ResponseEntity.ok(addgesttran);
}

}

@Autowired
DeviseService deviseserv;


  @GetMapping(produces = "application/json")
  @RequestMapping(value = "/admin/devises",method = RequestMethod.POST)
  public ResponseEntity<?> adddevise(@RequestBody AdddeviseRequest adddevise) throws Exception { {

    Devise devise= new Devise();
   devise.setName(adddevise.getName());
   devise.setExchangeRate(adddevise.getExchangeRate());

      deviseserv.create(devise);
      return ResponseEntity.ok(adddevise);
  }
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

     }


     @Autowired
    CommissionService commissionser;

        @GetMapping(produces = "application/json")
        @RequestMapping(value = "/admin/commissions",method = RequestMethod.POST)
        public ResponseEntity<?> addgestagence(@RequestBody Addcommissionrequest addcommission) throws Exception { {
          Commission comm = new Commission();

          comm.setTVA(addcommission.getTVA());
          comm.setTransfert(addcommission.getTransfert());


           commissionser.create(comm);

            return ResponseEntity.ok(addcommission);
        }

        }

}

