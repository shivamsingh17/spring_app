package com.stackroute.moneynmonetary.otpservice.controller;

import com.stackroute.moneynmonetary.otpservice.model.Customer;
import com.stackroute.moneynmonetary.otpservice.model.OtpVerification;
import com.stackroute.moneynmonetary.otpservice.service.OtpService;
import com.stackroute.moneynmonetary.otpservice.template.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/otp")
public class OtpController {
    /*
     * Autowiring should be implemented for the NewsService. Please note that we
     * should not create any object using the new keyword
     */
    @Autowired
    public OtpService otpService;
    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody() OtpVerification otpVerification){
        try {
            //Generate The Template to send OTP
            int otp = otpService.getOtp(otpVerification.getCustId().toString());
            if(otp==otpVerification.getOtp())return new ResponseEntity<>(HttpStatus.CREATED);
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/generateOtp")
    public ResponseEntity<?> sendOtp(@RequestBody() Customer customer){
        try {
            //Generate The Template to send OTP
            int otp = otpService.generateOtp(customer.getId().toString());
            EmailTemplate template = new EmailTemplate("Templates/SendOtp.html");
            Map<String,String> replacements = new HashMap<String,String>();
            replacements.put("user", customer.getFirstName()+" "+customer.getLastName());
            StringBuilder stringOtp= new StringBuilder(String.valueOf(otp));
            if(stringOtp.length()<6){
                for(int i=0;i<6-stringOtp.length();i++){
                    stringOtp.insert(0,"0");
                }
            }
            replacements.put("otpnum", stringOtp.toString());
            String message = template.getTemplate(replacements);
            otpService.sendOtp(customer.getEmailId(), message,stringOtp.toString() );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
