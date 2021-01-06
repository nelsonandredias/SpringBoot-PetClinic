package com.polarising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.polarising.controllers.ConstructorInjectedController;
import com.polarising.controllers.I18nController;
import com.polarising.controllers.MyController;
import com.polarising.controllers.PropertyInjectedController;
import com.polarising.controllers.SetterInjectedController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.polarisers.services","com.polarising"})
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DependencyInjectionApplication.class, args);
	
		ApplicationContext appContext = SpringApplication.run(DependencyInjectionApplication.class, args);
	
		System.out.println("----------------- My Controller --------------");
		
		MyController myController = (MyController) appContext.getBean("myController");
		
		System.out.println(myController.getGreeting());
		
		System.out.println("----------------- Property Injected Controller --------------");
		
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) appContext.getBean("propertyInjectedController");
		
		System.out.println(propertyInjectedController.getGreeting());
		
		System.out.println("----------------- Setter Injected Controller --------------");
		
		SetterInjectedController setterInjectedController = (SetterInjectedController) appContext.getBean("setterInjectedController");
		
		System.out.println(setterInjectedController.getGreeting());
		
		System.out.println("----------------- Constructor Injected Controller --------------");
		
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) appContext.getBean("constructorInjectedController");
	
		System.out.println(constructorInjectedController.getGreeting());
		
System.out.println("----------------- I18N Controller --------------");
		
		I18nController i18nController =  (I18nController) appContext.getBean("i18nController");
	
		System.out.println(i18nController.sayHello());
		
	}

}
