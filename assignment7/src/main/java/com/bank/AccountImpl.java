package com.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AccountImpl implements Account{
    private int id;
    private String type;
    private double balance;
    
    @Autowired
    @Qualifier("consoleLogger")
    private Logger logger;
    

    public AccountImpl() {
    }
    public AccountImpl(int id, String type, double balance) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "AccountImpl [id=" + id + ", type=" + type + ", balance=" + balance + "]";
    }

    public void deposit(double amount)
    {
        this.balance=this.balance+amount;
        if(logger!=null)
            logger.log("Desposit Rs. "+amount+"/- to account "+id);

    }

    public void withdraw(double amount)
    {
        this.balance=this.balance-amount;
        if(logger!=null)
            logger.log("Withdraw Rs. "+amount+"/- from account "+id);


    }
    public Logger getLogger() {
        return logger;
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }



}
