package com.polarising.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.polarising.models.FakeDatasource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class PropertyConfig {
	
	@Value("${polarising.username}")
	String user;
	@Value("${polarising.password}")
	String password;
	@Value("${polarising.dburl}")
	String url;

	
	@Bean
	public FakeDatasource fakeDataSource() {
		FakeDatasource fakeDataSource = new FakeDatasource();
		fakeDataSource.setUser(user);
		fakeDataSource.setPassword(password);
		fakeDataSource.setUrl(url);
		return fakeDataSource;
	}
	

	//this PropertySourcesPlaceholderConfigurer is what is going to read the properties file
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;
	}
	
}
