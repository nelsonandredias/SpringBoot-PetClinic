package com.polarising.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleBean implements InitializingBean, DisposableBean, BeanNameAware,
		BeanFactoryAware, ApplicationContextAware{

	
	public LifeCycleBean() {
		super();
		System.out.println("------------------- I'm in the LifecycleBean Constructor");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("------------------- The application context has been set");
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("------------------- The LifecycleBean factory has been set");
		
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("------------------- The LifecycleBean name is: " + name);
		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("------------------- The LifecycleBean has been terminated");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("------------------- The LifecycleBean has its properties set");
		
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("------------------- The Post Construct annotated has been called");
		
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("------------------- The Pre Destroy annotated has been called");
	}

	public void beforeInit() {
		System.out.println("------------------- The Bean before initialization has been called");
		
	}

	public void afterInit() {
		System.out.println("------------------- The Bean after initialization has been called");
		
	}
	
}
