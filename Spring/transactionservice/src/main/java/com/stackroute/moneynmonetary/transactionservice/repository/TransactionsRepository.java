package com.stackroute.moneynmonetary.transactionservice.repository;

import com.stackroute.moneynmonetary.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByCustomerId(UUID id);

    @Query(value = "SELECT id, amount, " +
            "CASE WHEN (recipient= :id) THEN receiver_current_balance " +
            "ELSE current_balance " +
            "END as current_balance, 0 as receiver_current_balance, customer_id, receiver_account_number, recipient, remarks, rounded_off_amount, time_stamp, customer_account_number, ifsc FROM transactions WHERE recipient= :id OR customer_id= :id ORDER BY time_stamp DESC",
            nativeQuery = true)
    List<Transaction> findAllTransaction(@Param("id") String id);
}