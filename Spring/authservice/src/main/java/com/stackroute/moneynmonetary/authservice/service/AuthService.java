package com.stackroute.moneynmonetary.authservice.service;

import com.stackroute.moneynmonetary.authservice.model.Customer;
import com.stackroute.moneynmonetary.authservice.model.Login;
import com.stackroute.moneynmonetary.authservice.util.JwtUtil;
import com.stackroute.moneynmonetary.authservice.util.exception.CustomerAlreadyExistsException;
import com.stackroute.moneynmonetary.authservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer register(Customer customerRequest) throws CustomerAlreadyExistsException {
        //do validation if user already exists
        try{
            customerRequest.setPassword(BCrypt.hashpw(customerRequest.getPassword(), BCrypt.gensalt()));
            Customer customer = restTemplate.postForObject("http://MoneynMonetaryCustomerservice/api/v1/customers/register", customerRequest, Customer.class);
            if(customer==null)throw new CustomerAlreadyExistsException();
            return customer;
        }catch (Exception e){
            throw new CustomerAlreadyExistsException();
        }
    }

    public Customer login(Login login) throws CustomerNotExistsException {
        //do validation if user already exists
        try{
            Customer customer = restTemplate.postForObject("http://MoneynMonetaryCustomerservice/api/v1/customers/authenticate", login, Customer.class);
            if(customer==null)throw new CustomerNotExistsException();
            customer.setJwtToken(jwtUtil.generateToken(login.getEmail()));
            return customer;
        }catch (Exception e){
            throw new CustomerNotExistsException();
        }

    }
}
