package com.polarising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.polarising.controllers.MyController;
import com.polarising.models.FakeDatasource;

@SpringBootApplication
public class BeanFactoryApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(BeanFactoryApplication.class, args);

		MyController controller = (MyController) ctx.getBean("myController");

		System.out.println(controller.hello());
		
		FakeDatasource fakeDataSource = (FakeDatasource) ctx.getBean(FakeDatasource.class);
		
		System.out.println("Datasource user: " + fakeDataSource.getUser() + ", password: " + fakeDataSource.getPassword() 
							+ ", url: " + fakeDataSource.getUrl());
		
	}

}
