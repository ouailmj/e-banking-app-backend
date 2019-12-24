package com.example.ebankingspg.java.Controller;

import com.authy.AuthyException;
import com.example.ebankingspg.java.model.dto.PhoneVerificationStartDTO;
import com.example.ebankingspg.java.model.dto.PhoneVerificationVerifyTransactionDTO;
import com.example.ebankingspg.java.services.PhoneVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/phone-verification")
public class PhoneVerificationController {

    private PhoneVerificationService phoneVerificationService;

    @Autowired
    public PhoneVerificationController(PhoneVerificationService phoneVerificationService) {
        this.phoneVerificationService = phoneVerificationService;
    }

    @RequestMapping(path = "start", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity start(@Valid @RequestBody PhoneVerificationStartDTO requestBody) throws AuthyException {

        phoneVerificationService.start(requestBody.getCountryCode(),
                requestBody.getPhoneNumber(),
                requestBody.getVia());
        System.out.println(requestBody);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(path = "verify", method = RequestMethod.POST)
    public ResponseEntity verify(@Valid @RequestBody PhoneVerificationVerifyTransactionDTO requestBody,
                                 HttpSession session) throws AuthyException {
        phoneVerificationService.verify(requestBody.getCountryCode(),
                requestBody.getPhoneNumber(),
                requestBody.getToken());

        session.setAttribute("ph_verified", true);
        return new ResponseEntity(HttpStatus.OK);
    }

}


