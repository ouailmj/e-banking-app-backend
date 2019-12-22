package com.example.ebankingspg.java.Controller;

import java.util.*;
import java.text.SimpleDateFormat;

import com.example.ebankingspg.java.Repository.*;
import com.example.ebankingspg.java.request.AddAccountRequest;
import com.example.ebankingspg.java.request.AddClientRequest;
import com.example.ebankingspg.java.request.RechargeAccountRequest;
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

import com.nexmo.client.HttpConfig;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.ebankingspg.java.model.*;

import javax.xml.ws.Response;

@CrossOrigin(origins = "*")
@RestController
public class GestClientController {

  @Autowired
  private GestClientService gestClientService;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AgenceRepository agenceRepository;

  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private DeviseRepository deviseRepository;

  @Autowired
  private CommissionRepository commissionRepository;

  @Autowired
  private TypeContratRepository typeContratRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private RoleRepository rolerep;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(produces = "application/json")
  @RequestMapping(value = "/manager_client/clients")
  public ResponseEntity<?> clients() throws Exception {
        Role role = roleRepository.findByRole("ROLE_CLIENT");
        Set<User> clients = role.getUsers();
        return ResponseEntity.ok(new ChartResponse(clients));
  }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/contrats" , method = RequestMethod.GET)
    public ResponseEntity<?> Contrats() throws Exception {
        List<TypeContrat> typeContrat = typeContratRepository.findAll();
        return ResponseEntity.ok(typeContrat);
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/getClient",method = RequestMethod.GET)
    public ResponseEntity<?> getclient(@RequestParam String id) throws Exception {
        Optional<Client> client = clientRepository.findById(Long.parseLong(id));
        client.orElseThrow(()->new Exception("User not found"));
        return ResponseEntity.ok(client.get());
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/updateClient",method = RequestMethod.POST)
    public ResponseEntity<?> updateclient(@RequestBody UpdateClientRequest updateClientRequest) throws Exception {
        Client client1 = updateClient(updateClientRequest);
        sendMessageSms(client1.getNumtel(),"Votre compte a été mis à jour avec succés. \n Bonne journée.");
        return ResponseEntity.ok(new StringResponse("user updated successfully"));
    }

    public void sendMessageSms(String to,String message){
        HttpConfig httpConfig = HttpConfig.defaultConfig();
        NexmoClient nxm = NexmoClient.builder()
                .apiKey("7416cfe6")
                .apiSecret("et6awwlhYX1di4lq")
                .build();
        SmsSubmissionResponse responses = nxm.getSmsClient().submitMessage(new TextMessage(
                "BANQUE ATLAS",
                "212" + to,
                message));
        for (SmsSubmissionResponseMessage response : responses.getMessages()) {
            System.out.println(response);
        }
    }

    public Client updateClient(UpdateClientRequest updateClientRequest) throws Exception {
        Optional<Client> client = clientRepository.findByEmail(updateClientRequest.getEmail());
        client.orElseThrow(()->new Exception("User not found"));
        Client client1 = client.get();
        client1.setFirstname(updateClientRequest.getFirstname());
        client1.setLastname(updateClientRequest.getLastname());
        client1.setAdress(updateClientRequest.getAddress());
        client1.setNumtel(updateClientRequest.getPhone());
        clientRepository.save(client1);
        return client1;
    }


    @GetMapping(produces = "application/json")
    public List<GestClient> firstPage() {
        List<GestClient> gestclients = gestClientService.findAll();
        return gestclients;
    }


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

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/dashboard")
    public ResponseEntity<?> dashboard() throws Exception {
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

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/chart")
    public ResponseEntity<?> chart() throws Exception {
        Role role = roleRepository.findByRole("ROLE_CLIENT");
        Set<User> clients = role.getUsers();
        return ResponseEntity.ok(new ChartResponse(clients));
    }

    static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/addAccount",method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody AddAccountRequest addAccountRequest) throws Exception {
        Account account = new Account();
        Optional<Client> client = clientRepository.findById(addAccountRequest.getId_client());
        Optional<TypeContrat> typeContrat = typeContratRepository.findByName(addAccountRequest.getTypecontrat());
        client.orElseThrow(()->new Exception("Client not found"));
        client.get().setIsValid(true);
        typeContrat.orElseThrow(()->new Exception("Contrat not found"));
        account.setBalance(addAccountRequest.getBalance());
        account.setBlockedbalance("0");
        account.setClient(client.get());
        account.setAccountvalidated(true);
        account.setTypecontrat(typeContrat.get());
        account.setTypeaccount(addAccountRequest.getTypecontrat());
        account.setActivation_date(new Date(System.currentTimeMillis()));
        boolean isNotValid = true;
        String str = client.get().getAgency().getName().substring(0,2);
        String rib = "AT" + str +getAlphaNumericString(10) + "MARAG1";
        while(isNotValid){
            Optional<Account> account1 = accountRepository.findByRib(rib);
            if(account1.isPresent()) {
                rib = "MK" + getAlphaNumericString(8) + "MARAG1";
            } else {
                isNotValid = false;
            }
        }
        account.setRib(rib);
        sendMessageSms(client.get().getNumtel(),"Votre compte "+ rib + " a été creer avec succés. \n Nous vous remercions.");
        accountRepository.save(account);
        return ResponseEntity.ok(new StringResponse("account created successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/rechargeAccount",method = RequestMethod.POST)
    public ResponseEntity<?> rechargeAccount(@RequestBody RechargeAccountRequest rechargeAccountRequest) throws Exception {
        Optional<Account> account = accountRepository.findByRib(rechargeAccountRequest.getRib());
        account.orElseThrow(()->new Exception("Account not found"));
        Account account1 = account.get();
        if(rechargeAccountRequest.getSomme()>100 && account1.isAccountvalidated()){
            account1.setBalance(account1.getBalance() + rechargeAccountRequest.getSomme());
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(account1.getClient().getEmail());
            msg.setSubject("Manager client banque");
            msg.setText("La recharge datante du " + new SimpleDateFormat("dd-MM-yyyy").format(new Date())
                    + " sur le compte " + rechargeAccountRequest.getRib() + " est passé avec succés");
            javaMailSender.send(msg);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Error Message");
        }
        accountRepository.save(account1);
        return ResponseEntity.ok(new StringResponse("account updated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/retraitAccount",method = RequestMethod.POST)
    public ResponseEntity<?> retraitAccount(@RequestBody RechargeAccountRequest rechargeAccountRequest) throws Exception {
        Optional<Account> account = accountRepository.findByRib(rechargeAccountRequest.getRib());
        account.orElseThrow(()->new Exception("Account not found"));
        Account account1 = account.get();
        if(account1.getBalance()> rechargeAccountRequest.getSomme()){
            account1.setBalance(account1.getBalance() - rechargeAccountRequest.getSomme());
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(account1.getClient().getEmail());
            msg.setSubject("Manager client banque");
            msg.setText("Le retrait datant du " + new SimpleDateFormat("dd-MM-yyyy").format(new Date())
                    + " sur le compte " + rechargeAccountRequest.getRib() + " est passé avec succés");
            javaMailSender.send(msg);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Error Message");
        }
        accountRepository.save(account1);
        return ResponseEntity.ok(new StringResponse("account updated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/desactivateAccount",method = RequestMethod.GET)
    public ResponseEntity<?> desactivateAccount(@RequestParam String rib) throws Exception {
        Optional<Account> account = accountRepository.findByRib(rib);
        account.orElseThrow(()->new Exception("Account not found"));
        Account account1 = account.get();
        account1.setAccountvalidated(false);
        sendMessageSms(account1.getClient().getNumtel(),"Votre compte "+ rib + " a été desactiver pour des raisons de sécurité.");
        accountRepository.save(account1);
        return ResponseEntity.ok(new StringResponse("account desactivated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/activateAccount",method = RequestMethod.GET)
    public ResponseEntity<?> activateAccount(@RequestParam String rib) throws Exception {
        Optional<Account> account = accountRepository.findByRib(rib);
        account.orElseThrow(()->new Exception("Account not found"));
        Account account1 = account.get();
        account1.setAccountvalidated(true);
        HttpConfig httpConfig = HttpConfig.defaultConfig();
        NexmoClient client = NexmoClient.builder()
                .apiKey("7416cfe6")
                .apiSecret("et6awwlhYX1di4lq")
                .build();
        SmsSubmissionResponse responses = client.getSmsClient().submitMessage(new TextMessage(
                "BANQUE ATLAS",
                "212" + account1.getClient().getNumtel(),
                "Votre compte "+ rib + " a été activer avec succés. \n Nous vous remercions."));
        for (SmsSubmissionResponseMessage response : responses.getMessages()) {
            System.out.println(response);
        }
        accountRepository.save(account1);
        return ResponseEntity.ok(new StringResponse("account activated successfully"));
    }

    @GetMapping(produces = "application/json")
    @RequestMapping(value = "/manager_client/addClient",method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody AddClientRequest addClientRequest) throws Exception {
        Optional<Client> cl = clientRepository.findByEmail(addClientRequest.getEmail());
        System.out.println(addClientRequest.getSubsciber());
        if(cl.isPresent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Error Message");
        }
        Client client = new Client();
        Role role = roleRepository.findByRole("ROLE_CLIENT");
        client.setEmail(addClientRequest.getEmail());
        client.setFirstname(addClientRequest.getFirstname());
        client.setLastname(addClientRequest.getLastname());
        client.setIsValid(false);
        client.setNumtel(addClientRequest.getPhone());
        client.setAdress(addClientRequest.getAddress());
        Set <Role> set = new HashSet<Role>();
        set.add(role);
        client.setRoles(set);
        String str = getAlphaNumericString(6);
        client.setPassword(bCryptPasswordEncoder.encode(str));
        System.out.println(client.getPassword());
        client.setStatus("client");
        Image image = new Image();
        Image image2 = new Image();
        GestClient gestClient = gestClientService.findById(addClientRequest.getSubsciber()).get();
        client.setAgency(gestClient.getAgency());
        clientRepository.save(client);
        image.setClient(client);
        image2.setClient(client);
        image.setUrl(addClientRequest.getImage().getImage1());
        image2.setUrl(addClientRequest.getImage().getImage2());
        imageRepository.save(image);
        imageRepository.save(image2);

        sendMessageSms(client.getNumtel(),"Votre compte a été creer avec succés. \n Votre mot de passe est:"+str);
        return ResponseEntity.ok(addClientRequest);
    }

}
