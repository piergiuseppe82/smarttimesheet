package com.github.piergiuseppe82.smarttimesheet.document.providers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider implements ApplicationContextAware {
	private  ApplicationContext CONTEXT;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 CONTEXT = applicationContext;
		
	}
	
	
	    public Object getBean(String beanName) {
	        return CONTEXT.getBean(beanName);
	    }

}
