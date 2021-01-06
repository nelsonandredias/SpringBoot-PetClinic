package com.polarising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.polarising.models.FakeCassandraDataSource;
import com.polarising.models.FakeDataSource;
import com.polarising.models.FakeJMSBroker;
import com.polarising.models.FakeRabbitMQDatasource;
import com.polarising.models.PolarisingDevTeams;

@SpringBootApplication
public class ExternalPropertiesApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(ExternalPropertiesApplication.class, args);
		
		FakeDataSource fakeDataSource = (FakeDataSource) ctx.getBean(FakeDataSource.class);
		
		System.out.println("Datasource user: " + fakeDataSource.getUser() + ", password: " + fakeDataSource.getPassword() 
							+ ", url: " + fakeDataSource.getUrl());
		
		FakeJMSBroker fakeJMSBroker = (FakeJMSBroker) ctx.getBean(FakeJMSBroker.class);
		
		System.out.println("JMS user: " + fakeJMSBroker.getUsername() + ", password: " + fakeJMSBroker.getPassword() 
							+ ", url: " + fakeJMSBroker.getUrl());
		
		FakeCassandraDataSource fakeCassandraDataSource = ctx.getBean(FakeCassandraDataSource.class);
	
		System.out.println("Cassandra user: " + fakeCassandraDataSource.getUser() + ", password: " + fakeCassandraDataSource.getPassword() 
		+ ", url: " + fakeCassandraDataSource.getUrl());
		
		FakeRabbitMQDatasource fakeRabbitMQDatasource = ctx.getBean(FakeRabbitMQDatasource.class);
		
		System.out.println("RabbitMQ user: " + fakeRabbitMQDatasource.getUsername() + ", password: " + fakeRabbitMQDatasource.getPassword() 
		+ ", url: " + fakeRabbitMQDatasource.getUrl());
		
		PolarisingDevTeams polarisingDevTeams = ctx.getBean(PolarisingDevTeams.class);
		
		System.out.println("Polarising Developers dev1: " + polarisingDevTeams.getDev1() + ", dev2: " + polarisingDevTeams.getDev2() 
		+ ", dev3: " + polarisingDevTeams.getDev3());

	}

}
