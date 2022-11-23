package com.stackroute.moneynmonetary.transactionservice.repository;

import com.stackroute.moneynmonetary.transactionservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByEmailId(String emailId);
}