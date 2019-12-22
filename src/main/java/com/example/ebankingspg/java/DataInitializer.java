package com.example.ebankingspg.java;

import com.example.ebankingspg.java.model.*;
import com.example.ebankingspg.java.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.example.ebankingspg.java.model.Admin.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final boolean LOAD_INITIAL_DATA = true;
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_CLIENT = "ROLE_CLIENT";
    private static final String ROLE_CLIENT_MANAGER = "ROLE_CLIENT_MANAGER";
    private static final String ROLE_TRANSACTION_MANAGER = "ROLE_TRANSACTION_MANAGER";

    //type contrat :
    private static final String TYPE_CONTRACT1 = "YOUTH ACCOUNT";
    private static final String TYPE_CONTRACT2 = "ORDINARY ACCOUNT";
    private static final String TYPE_CONTRACT3 = "PLATINIUM ACCOUNT";
    private static final String TYPE_CONTRACT4 = "GOLD ACCOUNT";

    private final UserService userService;
    private final TypeContractService typeContractService;
    private final AdminService adminService;
    private final ClientService clientService;
    private final GestClientService gestClientService;
    private final GestTransacService gestTransacService;
    private final RoleService roleService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AgenceService agenceService;
    private final CommissionService commissionService;
    private final DeviseService deviseService;
    private final OperatorService operatorService;

    @Autowired
    public DataInitializer(UserService userService, AdminService adminService, ClientService clientService, GestClientService gestClientService, GestTransacService gestTransacService, RoleService roleService, TypeContractService typeContractService, AccountService accountService, TransactionService transactionService, AgenceService agenceService, CommissionService commissionService, DeviseService deviseService, OperatorService operatorService) {
        this.userService = userService;
        this.adminService = adminService;
        this.clientService = clientService;
        this.gestClientService = gestClientService;
        this.gestTransacService = gestTransacService;
        this.roleService = roleService;
        this.typeContractService = typeContractService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.agenceService = agenceService;
        this.commissionService = commissionService;
        this.deviseService = deviseService;
        this.operatorService = operatorService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(LOAD_INITIAL_DATA){

            Commission commission = Commission.builder().transfert(10).TVA(10).build();
            commissionService.create(commission);

            Devise devise = Devise.builder().exchangeRate(10).name("DHS").build();
            deviseService.create(devise);

            Role role_client_manager = Role.builder().role(ROLE_CLIENT_MANAGER).build();
            Role role_client = Role.builder().role(ROLE_CLIENT).build();
            Role role_admin = Role.builder().role(ROLE_ADMIN).build();
            Role role_transaction_manager = Role.builder().role(ROLE_TRANSACTION_MANAGER).build();

            // aniss
            TypeContrat typeContrat1 = TypeContrat.builder().name(TYPE_CONTRACT1).Rate(2).build();
            TypeContrat typeContrat2 = TypeContrat.builder().name(TYPE_CONTRACT2).Rate(3).build();
            TypeContrat typeContrat3 = TypeContrat.builder().name(TYPE_CONTRACT3).Rate(2).build();
            TypeContrat typeContrat4 = TypeContrat.builder().name(TYPE_CONTRACT4).Rate(0).build();

            typeContractService.create(typeContrat1);
            typeContractService.create(typeContrat2);
            typeContractService.create(typeContrat3);
            typeContractService.create(typeContrat4);

            roleService.create(role_admin);
            roleService.create(role_client);
            roleService.create(role_client_manager);
            roleService.create(role_transaction_manager);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Set<Role> roles = new HashSet<Role>();
            roles.add(roleService.findByRole(ROLE_ADMIN));

            Admin admin = builder().
                    email("admin@gmail.com").
                    password(passwordEncoder.encode("admin")).
                    roles(roles).isValid(true)
                    .firstname("test")
                    .lastname("test")
                    .adress("marrakech")
                    .numtel("642215381")
                    .status("test")
                    .build();

            userService.create(admin);

            Set<Role> roles1 = new HashSet<Role>();
            roles1.add(roleService.findByRole(ROLE_CLIENT));

            Agency agency = Agency.builder().name("agenceAtlas1").build();
            agenceService.create(agency);
            Client client = Client.
                    builder()
                    .email("client@gmail.com")
                    .password(passwordEncoder.encode("client"))
                    .isValid(true)
                    .roles(roles1)
                    .adress("marrakech")
                    .firstname("test")
                    .lastname("test")
                    .numtel("642215381")
                    .status("test")
                    .agency(agency)
                    .build();



            userService.create(client);


            Set<Role> roles2 = new HashSet<Role>();
            roles2.add(roleService.findByRole(ROLE_CLIENT_MANAGER));
            GestClient clientManager = GestClient.builder()
                    .email("client_manager@gmail.com")
                    .password(passwordEncoder.encode("client_manager"))
                    .isValid(true)
                    .adress("Marrakech")
                    .firstname("test")
                    .lastname("test")
                    .numtel("0642215381")
                    .status("test")
                    .roles(roles2).build();
            userService.create(clientManager);


            //anass
            Account account1=Account.builder().client(client).accountvalidated(true).balance(10000000).rib("ATAG334545633MARR").typeaccount(typeContrat1.getName()).blockedbalance("2000").typecontrat(typeContrat1).build();
            Account account2=Account.builder().client(client).accountvalidated(true).balance(70000000).rib("ATAG675768678MARR").typeaccount(typeContrat1.getName()).blockedbalance("2000").typecontrat(typeContrat1).build();
            accountService.create(account1);
            accountService.create(account2);

            Transaction transaction2=Transaction.builder().account(account1).active(false).amount(2000).accountTarget(account2).build();
            transactionService.create(transaction2);

            Set<Role> roles3 = new HashSet<Role>();
            roles3.add(roleService.findByRole(ROLE_TRANSACTION_MANAGER));
            GestTransac transactionManager = GestTransac.builder().email("transaction_manager@gmail.com").password(passwordEncoder.encode("transaction_manager")).isValid(true).roles(roles3).build();
            userService.create(transactionManager);

            Operator operator = Operator.builder().name("operator GSM").build();
            operatorService.create(operator);

        }

    }

}
