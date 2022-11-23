package com.stackroute.moneynmonetary.customerservice.controller;

import com.stackroute.moneynmonetary.customerservice.model.AccountVerification;
import com.stackroute.moneynmonetary.customerservice.model.Customer;
import com.stackroute.moneynmonetary.customerservice.model.Login;
import com.stackroute.moneynmonetary.customerservice.service.CustomerService;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerAlreadyExistsException;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/customers")
public class CustomerController {
    /*
     * Autowiring should be implemented for the NewsService. Please note that we
     * should not create any object using the new keyword
     */
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getCustomers(){
        List<Customer> custoemerList = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(custoemerList, HttpStatus.OK);
    }
    @GetMapping("/{custId}")
    public ResponseEntity<?> getCustomer(@PathVariable("custId") UUID custId){
        try {
            Customer search = customerService.getCustomer(custId);
            return new ResponseEntity<Customer>(search,HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/balances/{custId}")
    public ResponseEntity<?> getBalances(@PathVariable("custId") UUID custId){
        try {
            Customer search = customerService.getBalances(custId);
            return new ResponseEntity<Customer>(search,HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byEmailId/{emailId}")
    public ResponseEntity<?> getCustomerUsingEmail(@PathVariable("emailId") String emailId){
        try {
            Customer search = customerService.getCustomerByEmailId(emailId);
            return new ResponseEntity<Customer>(search,HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> addCustomer(@RequestBody() Customer customer){
        try {
            Customer addedCustomer = customerService.addCustomer(customer);
            return new ResponseEntity<Customer>(addedCustomer,HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            String a1=e.getMessage();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody() Login login) throws Exception{
        try {
            Customer addedCustomer = customerService.getCustomerUsingLogin(login);
            return new ResponseEntity<Customer>(addedCustomer,HttpStatus.CREATED);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e){
            throw new Exception("Invalid username/password");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody()AccountVerification verification){
        try {
            Customer verified = customerService.verifyCustomerUsingAcc(verification);
            return new ResponseEntity<Customer>(verified,HttpStatus.CREATED);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/{custId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("custId") UUID custId, @RequestBody Customer customer){
        try {
            customerService.getCustomer(custId);
            Customer updatedNews = customerService.updateCustomer(customer);
            return new ResponseEntity<Customer>(updatedNews,HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{custId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("custId") UUID custId){
        try {
            customerService.deleteCustomer(custId);
            return new ResponseEntity<String>("Customer Deleted",HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}
