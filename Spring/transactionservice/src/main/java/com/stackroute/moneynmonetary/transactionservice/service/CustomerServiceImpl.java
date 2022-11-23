package com.stackroute.moneynmonetary.transactionservice.service;

import com.stackroute.moneynmonetary.transactionservice.model.Customer;
import com.stackroute.moneynmonetary.transactionservice.repository.CustomerRepository;
import com.stackroute.moneynmonetary.transactionservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    /*
     * Autowiring should be implemented for the CustomerRepository.
     */
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(UUID custId) throws CustomerNotExistsException {
        Customer search = null;
        Optional<Customer> check = customerRepository.findById(custId);
        if (check.isPresent()) {
            search = check.get();
        } else {
            throw new CustomerNotExistsException();
        }
        return search;
    }
    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotExistsException {
        try{
            Customer search = customerRepository.getReferenceById(customer.getId());
            customerRepository.saveAndFlush(customer);
            return search;
        }catch(EntityNotFoundException exception){
            throw new CustomerNotExistsException();
        }
    }

}
