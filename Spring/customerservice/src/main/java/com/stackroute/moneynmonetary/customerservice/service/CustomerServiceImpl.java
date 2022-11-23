package com.stackroute.moneynmonetary.customerservice.service;

import com.stackroute.moneynmonetary.customerservice.model.AccountVerification;
import com.stackroute.moneynmonetary.customerservice.model.Customer;
import com.stackroute.moneynmonetary.customerservice.model.Login;
import com.stackroute.moneynmonetary.customerservice.repository.CustomerRepository;
import com.stackroute.moneynmonetary.customerservice.repository.GlobalKeyValues;
import com.stackroute.moneynmonetary.customerservice.repository.GlobalKeyValuesRepository;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerAlreadyExistsException;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerNotAuthorisedExcepton;
import com.stackroute.moneynmonetary.customerservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    /*
     * Autowiring should be implemented for the CustomerRepository.
     */
    @Autowired
    CustomerRepository customerRepository;
    /*
     * Autowiring should be implemented for the CustomerRepository.
     */
    @Autowired
    GlobalKeyValuesRepository globalKeyValuesRepository;
    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        Customer added = null;
        try{
            if(customer.getId()==null)throw new EntityNotFoundException();
            customerRepository.getReferenceById(customer.getId());
            throw new CustomerAlreadyExistsException();
        }catch(EntityNotFoundException e){
        	Optional<GlobalKeyValues> check = globalKeyValuesRepository.findByKeyString("last_acc_no");
            if(check.isEmpty()) {
            	globalKeyValuesRepository.save(new GlobalKeyValues("last_acc_no","1000000000"));
                customer.setAccNo(1000000000L);
            }
            else {
                GlobalKeyValues tuple=check.get();
                Long newAccNo=Long.parseLong(tuple.getValue())+1;
                tuple.setValue(newAccNo.toString());
                globalKeyValuesRepository.saveAndFlush(tuple);
                customer.setAccNo(newAccNo);
            }
            customer.setIfscCode("RBS000890");
            customer.setCheckinBalance(5000.00d);
            customer.setProfileUrl("https://demos.creative-tim.com/argon-dashboard/assets-old/img/theme/team-4.jpg");
            customer.setCurrency("$");
            added = customerRepository.save(customer);
        }
        return added;
    }

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
    public Customer getBalances(UUID custId) throws CustomerNotExistsException {
        Customer search = null;
        Optional<Customer> check = customerRepository.findById(custId);
        if (check.isPresent()) {
            search = check.get();
            search=new Customer(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,search.getCheckinBalance(),search.getSavingsBalance(),null);
        } else {
            throw new CustomerNotExistsException();
        }
        return search;
    }
    @Override
    public Customer getCustomerUsingLogin(Login login) throws CustomerNotExistsException, CustomerNotAuthorisedExcepton {
        Customer search = customerRepository.findByEmailId(login.getEmail());
        if (search!=null) {
            if(BCrypt.hashpw(login.getPassword(),search.getPassword()).equals(search.getPassword()))return search;
            else{
                throw new CustomerNotAuthorisedExcepton();
            }
        } else {
            throw new CustomerNotExistsException();
        }
    }

    @Override
    public Customer getCustomerByEmailId(String emailId) throws CustomerNotExistsException {
        Customer search = null;
        Customer check = customerRepository.findByEmailId(emailId);
        if (check!=null) {
            search = check;
        } else {
            throw new CustomerNotExistsException();
        }
        return search;
    }
    

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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

    @Override
    public void deleteCustomer(UUID custId) throws CustomerNotExistsException {
        try{
            customerRepository.getReferenceById(custId);
            customerRepository.deleteById(custId);
        }catch (EntityNotFoundException exception){
            throw new CustomerNotExistsException();
        }
    }

	@Override
	public Customer verifyCustomerUsingAcc(AccountVerification verification) throws CustomerNotExistsException {
        Customer search = customerRepository.findByAccountDetails(verification.getAccNo(),verification.getIfscCode());
        if (search!=null) {
            return search;
        } else {
            throw new CustomerNotExistsException();
        }
	}

}
