package com.stackroute.moneynmonetary.transactionservice.service;


import com.stackroute.moneynmonetary.transactionservice.model.Customer;
import com.stackroute.moneynmonetary.transactionservice.util.exception.CustomerNotExistsException;


import java.util.List;
import java.util.UUID;

public interface CustomerService {
    public Customer getCustomer(UUID custId) throws CustomerNotExistsException;

    public Customer updateCustomer(Customer customer) throws CustomerNotExistsException;
}
