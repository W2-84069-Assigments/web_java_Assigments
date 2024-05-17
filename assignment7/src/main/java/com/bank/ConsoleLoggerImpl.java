package com.bank;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component()
@Qualifier("consoleLogger")
public class ConsoleLoggerImpl implements Logger

{
    @Override
    public void log(String message) {
        System.out.println("CONSOLE "+message);
    }
}
