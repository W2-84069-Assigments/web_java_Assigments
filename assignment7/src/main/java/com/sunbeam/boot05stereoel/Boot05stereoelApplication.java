package com.sunbeam.boot05stereoel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.bank.Account;
import com.bank.BankConfig;
import com.bank.ConsoleLoggerImpl;
import com.bank.FileLoggerImpl;
import com.bank.Logger;
import com.bank.TestSpEL;

@Import(BankConfig.class)
@SpringBootApplication
public class Boot05stereoelApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Boot05stereoelApplication.class, args);
}
	@Autowired
	private ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception 
	{

		ConsoleLoggerImpl consoleLogger= ctx.getBean(ConsoleLoggerImpl.class);
		consoleLogger.log("Hello, Console!");
		FileLoggerImpl fileLogger=ctx.getBean(FileLoggerImpl.class);					 
		fileLogger.log("Hello,File!");
		Logger logger = ctx.getBean(Logger.class);
		logger.log("Hello,DMC");

		Account accref = ctx.getBean(Account.class);
		System.out.println("acc: "+accref.toString());

		//accref.setLogger(consoleLogger);
		accref.deposit(8000);
		System.out.println("acc: "+accref.toString());

		//accref.setLogger(fileLogger);
		accref.withdraw(3000);
		System.out.println("acc: "+accref.toString());

		TestSpEL testSpEL=ctx.getBean(TestSpEL.class);
		testSpEL.display();



	}

}
