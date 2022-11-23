package com.stackroute.moneynmonetary.customerservice.service;

import com.stackroute.moneynmonetary.customerservice.model.AccountVerification;
import com.stackroute.moneynmonetary.customerservice.model.Customer;
import com.stackroute.moneynmonetary.customerservice.model.Login;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerAlreadyExistsException;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerNotAuthorisedExcepton;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerNotExistsException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;

    public Customer getCustomer(UUID custId) throws CustomerNotExistsException;
    public Customer getBalances(UUID custId) throws CustomerNotExistsException;
    public Customer getCustomerUsingLogin(Login login) throws CustomerNotExistsException, CustomerNotAuthorisedExcepton;

    public List<Customer> getAllCustomers();

    public Customer updateCustomer(Customer customer) throws CustomerNotExistsException;

    public void deleteCustomer(UUID custId) throws CustomerNotExistsException;
    
    public Customer verifyCustomerUsingAcc(AccountVerification verification) throws CustomerNotExistsException;

    public Customer getCustomerByEmailId(String emailId)throws CustomerNotExistsException;
}
