package com.bank;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileLoggerImpl implements Logger
{   
    @Value("${logFile}")
     private String logFilePath="applog.txt";

    @Override
    public void log(String message) {
       try(FileOutputStream fout=new FileOutputStream(logFilePath,true))
       {
        message +="\n";
        fout.write(message.getBytes());
       }
       catch(Exception e)
       {
        e.printStackTrace();
       }

    }


}
