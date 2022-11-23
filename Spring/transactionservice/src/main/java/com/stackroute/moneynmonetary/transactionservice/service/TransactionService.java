package com.stackroute.moneynmonetary.transactionservice.service;


import com.stackroute.moneynmonetary.transactionservice.model.Transaction;
import com.stackroute.moneynmonetary.transactionservice.util.exception.CustomerNotExistsException;
import com.stackroute.moneynmonetary.transactionservice.util.exception.TransactionAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistsException, CustomerNotExistsException;

    public List<Transaction> getAllTransactions(UUID id);
}
