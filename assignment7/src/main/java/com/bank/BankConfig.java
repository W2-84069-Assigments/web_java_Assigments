package com.bank;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.bank")
@Configuration
public class BankConfig 
{
    @Bean
    public Account acc(){
        Account acc= new AccountImpl();
        acc.setId(101);
        acc.setType("Saving");
        acc.setBalance(10000);
        return acc;
    }

    @Primary
    @Bean
    public Account acc2(){
        Account acc2= new AccountImpl();
        acc2.setId(109);
        acc2.setType("Saving");
        acc2.setBalance(5000);
        return acc2;
    }

}
