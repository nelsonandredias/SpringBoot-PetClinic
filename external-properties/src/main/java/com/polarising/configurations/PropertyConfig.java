package com.polarising.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.polarising.models.FakeCassandraDataSource;
import com.polarising.models.FakeDataSource;
import com.polarising.models.FakeJMSBroker;
import com.polarising.models.FakeRabbitMQDatasource;
import com.polarising.models.PolarisingDevTeams;


@Configuration
@PropertySource({"classpath:datasource.properties","classpath:jms.properties"})
public class PropertyConfig {
	
	@Value("${polarising.username}")
	String user;
	@Value("${polarising.password}")
	String password;
	@Value("${polarising.dburl}")
	String url;
	
	
	@Value("${polarising.jms.username}")
	String jmsUsername;
	@Value("${polarising.jms.password}")
	String jmsPassword;
	@Value("${polarising.jms.dburl}")
	String jmsUrl;
	
	@Value("${polarising.cassandra.username}")
	String cassandraUsername;
	@Value("${polarising.cassandra.password}")
	String cassandraPassword;
	@Value("${polarising.cassandra.dburl}")
	String cassandraUrl;
	
	@Value("${polarising.rabbitmq.username}")
	String rabbitMQUsername;
	@Value("${polarising.rabbitmq.password}")
	String rabbitMQPassword;
	@Value("${polarising.rabbitmq.url}")
	String rabbitMQUrl;
	
	@Value("${polarising.developers.dev1}")
	String dev1;
	@Value("${polarising.developers.dev2}")
	String dev2;
	@Value("${polarising.developers.dev3}")
	String dev3;
	
	@Bean
	public FakeDataSource fakeDataSource() {
		FakeDataSource fakeDataSource = new FakeDataSource();
		fakeDataSource.setUser(user);
		fakeDataSource.setPassword(password);
		fakeDataSource.setUrl(url);
		return fakeDataSource;
	}
	
	@Bean
	public FakeJMSBroker fakeJMSBroker() {
		FakeJMSBroker fakeJMSBroker = new FakeJMSBroker();
		fakeJMSBroker.setUsername(jmsUsername);
		fakeJMSBroker.setPassword(jmsPassword);
		fakeJMSBroker.setUrl(jmsUrl);
		return fakeJMSBroker;
	}
	
	@Bean
	public FakeCassandraDataSource fakeCassandraDataSource() {
		FakeCassandraDataSource fakeCassandraDataSource = new FakeCassandraDataSource();
		fakeCassandraDataSource.setUser(cassandraUsername);
		fakeCassandraDataSource.setPassword(cassandraPassword);
		fakeCassandraDataSource.setUrl(cassandraUrl);
		return fakeCassandraDataSource;
	}
	
	@Bean
	public FakeRabbitMQDatasource fakeRabbitMQDatasource() {
		FakeRabbitMQDatasource fakeRabbitMQDatasource = new FakeRabbitMQDatasource();
		fakeRabbitMQDatasource.setUsername(rabbitMQUsername);
		fakeRabbitMQDatasource.setPassword(rabbitMQPassword);
		fakeRabbitMQDatasource.setUrl(rabbitMQUrl);
		return fakeRabbitMQDatasource;
	}
	
	@Bean
	public PolarisingDevTeams polarisingDevTeams() {
		PolarisingDevTeams polarisingDevTeams = new PolarisingDevTeams();
		polarisingDevTeams.setDev1(dev1);
		polarisingDevTeams.setDev2(dev2);
		polarisingDevTeams.setDev3(dev3);
		return polarisingDevTeams;
	}
	
	//this PropertySourcesPlaceholderConfigurer is what is going to read the properties file
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;
	}
	
}
