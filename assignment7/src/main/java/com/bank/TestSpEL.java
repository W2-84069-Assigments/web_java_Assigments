package com.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestSpEL {
    @Value("#{acc2.id}")
    private int accId;
    @Value("#{acc2.getBalance()}")
    private double accBalance;
    @Value("#{acc2}")
    private Account a;

    public void display(){
        System.out.println("accId= "+accId);
        System.out.println("accBalance= "+accBalance);
        System.out.println("a= "+a);
    }
}
