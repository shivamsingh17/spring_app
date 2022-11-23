package com.stackroute.moneynmonetary.transactionservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private double amount;
    private long roundedOffAmount;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime timeStamp;
    private Long receiverAccountNumber;
    private Long customerAccountNumber;
    private String remarks;

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    private String ifsc;
    private double currentBalance;
    private double receiverCurrentBalance;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID recipient;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID customerId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public UUID getRecipient() {
        return recipient;
    }

    public void setRecipient(UUID recipient) {
        this.recipient = recipient;
    }

    public double getReceiverCurrentBalance() {
        return receiverCurrentBalance;
    }

    public void setReceiverCurrentBalance(double receiverCurrentBalance) {
        this.receiverCurrentBalance = receiverCurrentBalance;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getRoundedOffAmount() {
        return roundedOffAmount;
    }

    public void setRoundedOffAmount(long roundedOffAmount) {
        this.roundedOffAmount = roundedOffAmount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(Long customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }

    public Transaction(){

    }
    public Transaction(UUID id, double amount, long roundedOffAmount, LocalDateTime timeStamp, Long receiverAccountNumber, Long customerAccountNumber, String remarks, String ifsc, double currentBalance, double receiverCurrentBalance, UUID recipient, UUID customerId) {
        this.id = id;
        this.amount = amount;
        this.roundedOffAmount = roundedOffAmount;
        this.timeStamp = timeStamp;
        this.receiverAccountNumber = receiverAccountNumber;
        this.customerAccountNumber = customerAccountNumber;
        this.remarks = remarks;
        this.ifsc = ifsc;
        this.currentBalance = currentBalance;
        this.receiverCurrentBalance = receiverCurrentBalance;
        this.recipient = recipient;
        this.customerId = customerId;
    }
    @Override
    public String toString(){
        return ""+this.amount+this.roundedOffAmount+this.timeStamp+this.recipient.toString()+this.customerId.toString();
    }
}