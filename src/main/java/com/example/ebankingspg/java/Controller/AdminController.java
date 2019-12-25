package com.example.ebankingspg.java.Controller;


import com.example.ebankingspg.java.Repository.*;
import com.example.ebankingspg.java.model.*;
import com.example.ebankingspg.java.request.*;
import com.example.ebankingspg.java.response.Agenceresponse;
import com.example.ebankingspg.java.response.ChartResponse;
import com.example.ebankingspg.java.response.Gestclientresponse;
import com.example.ebankingspg.java.response.StringResponse;
import com.example.ebankingspg.java.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Role role = rolerep.findByRole("ROLE_CLIENT_MANAGER");
        GestClient gestcli = new GestClient();
        gestcli.setEmail(addgestclient.getEmail());
        gestcli.setFirstname(addgestclient.getFirstname());
        gestcli.setLastname(addgestclient.getLastname());
        gestcli.setIsValid(false);
        gestcli.setNumtel(addgestclient.getPhone());
        gestcli.setAdress(addgestclient.getAddress());
        Optional<Agency> agnc =agencerep.findById(addgestclient.getIdagence());
        gestcli.setAgency(agnc.get());
        String pass = addgestclient.getPassword();
        String newpass = bCryptPasswordEncoder.encode(pass);
        gestcli.setPassword(newpass);
        Set<Role> set = new HashSet<Role>();
        set.add(role);
        gestcli.setRoles(set);

        gestClientService.create(gestcli);
        return ResponseEntity.ok(addgestclient);
    }

    }
    @Autowired

    GestTransacService gesttran;



    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/addgesttransac",method = RequestMethod.POST)
    public ResponseEntity<?> addgesttransac(@RequestBody Addgesttransacrequest addgesttran) throws Exception { {
        Role role = rolerep.findByRole("ROLE_TRANSACTION_MANAGER");
        GestTransac gestcli = new GestTransac();
        gestcli.setEmail(addgesttran.getEmail());
        gestcli.setFirstname(addgesttran.getFirstname());
        gestcli.setLastname(addgesttran.getLastname());
        gestcli.setIsValid(false);
        gestcli.setNumtel(addgesttran.getPhone());
        gestcli.setAdress(addgesttran.getAddress());
        agencerep.findById(addgesttran.getIdagence());
        Optional<Agency> agnc =agencerep.findById(addgesttran.getIdagence());
        gestcli.setAgency(agnc.get());
        String pass = addgesttran.getPassword();
        String newpass = bCryptPasswordEncoder.encode(pass);
        gestcli.setPassword(newpass);
        Set <Role> set = new HashSet<Role>();
        set.add(role);
        gestcli.setRoles(set);

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
TypeContractService typecontratService;

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/typecontrats",method = RequestMethod.POST)
    public ResponseEntity<?> addtypecontrat(@RequestBody Addtypecontrat typecontrat) throws Exception { {

        TypeContrat devise= new TypeContrat();
        devise.setName(typecontrat.getName());
        devise.setRate(typecontrat.getRate());

        typecontratService.create(devise);
        return ResponseEntity.ok(typecontrat);
    }
    }
    @Autowired
    AgenceService agenceservice;

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/addagences",method = RequestMethod.POST)
    public ResponseEntity<?> addgestagence(@RequestBody Addagencerequest addagence) throws Exception { {
        Agency agence = new Agency();

        agence.setName(addagence.getName());
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
    @Autowired
    UserRepository userrep;

    @Autowired
    RoleService roleserv;

    @Autowired
    GestClientRepository gestcli;

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/gestionnaireclients")
    public ResponseEntity<?> gestionnaireclients() throws Exception {
        //Role role = rolerep.findByRole("ROLE_CLIENT_MANAGER");
        //Set<User> hSet= role.getUsers();
        List<GestClient> hset2= gestcli.findAll();
        Set<GestClient> hSet = new HashSet<GestClient>();
        for (GestClient x : hset2)
            hSet.add(x);

        return ResponseEntity.ok(new Gestclientresponse(hSet));
    }

    @Autowired
    UserService userservice;

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/gesttransacs")
    public ResponseEntity<?> gesttransactions() throws Exception {
        Role role = rolerep.findByRole("ROLE_TRANSACTION_MANAGER");
        Set<User> hSet= role.getUsers();

        return ResponseEntity.ok(new ChartResponse(hSet));

    }

    @Autowired
    AgenceRepository agencerep;
    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/getagences")
    public ResponseEntity<?> gestagences() throws Exception {

        List<Agency> agence = agencerep.findAll();

        Set<Agency> hSet = new HashSet<Agency>();
        for (Agency x : agence)
            hSet.add(x);


        return ResponseEntity.ok(new Agenceresponse(hSet));

    }



    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/activateUser",method = RequestMethod.GET)
    public ResponseEntity<?> unbannUser(@RequestParam Long id) throws Exception {
        Optional<User> user = userrep.findById(id);
        user.orElseThrow(()->new Exception("User not found"));
        User user1 = user.get();
        user1.setIsValid(true);
        userrep.save(user1);
        return ResponseEntity.ok(new StringResponse("user activated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/desactivateUser",method = RequestMethod.GET)
    public ResponseEntity<?> bannUser(@RequestParam Long id) throws Exception {
        Optional<User> user = userrep.findById(id);
        user.orElseThrow(()->new Exception("User not found"));
        User user1 = user.get();
        user1.setIsValid(false);
        userrep.save(user1);
        return ResponseEntity.ok(new StringResponse("user banned successfully"));
    }


    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/admin/deleteUser",method = RequestMethod.GET)
    public ResponseEntity<?> deleteUser(@RequestParam Long id) throws Exception {
        Optional<User> user = userrep.findById(id);
        user.orElseThrow(()->new Exception("User not found"));
        User user1 = user.get();

        userrep.delete(user1);
        return ResponseEntity.ok(new StringResponse("user deleted successfully"));
    }
}

