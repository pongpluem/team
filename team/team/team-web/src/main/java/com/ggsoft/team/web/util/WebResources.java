/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ggsoft.team.web.util;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class WebResources implements Serializable{

	private static final long serialVersionUID = 1L;

	@Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
	@Named
    @Produces
	public Logger produceWebLog(InjectionPoint injectionPoint){ 
		return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
	
	@Named
	@Produces
    @SystemProperty(value="")
	public String findProperty(InjectionPoint ip) {
        SystemProperty annotation = ip.getAnnotated()
            .getAnnotation(SystemProperty.class);

        String name = annotation.value();
        String found = System.getProperty(name);
        if (found == null) {
        	found =FacesContext.getCurrentInstance().getExternalContext().getInitParameter(name);
        	if(found==null){
        		throw new IllegalStateException("System property '" + name + "' is not defined!");
        	}
        }
        return found;
    }
	
	@Named
    @Produces
    public ResourceBundle appConfigResource(){    		
        	return ResourceBundle.getBundle("AppConfig");
	}


	@Named
    @Produces
    public ResourceBundle labelMessageResource(){
    	FacesContext ctx = FacesContext.getCurrentInstance();
    	if (ctx.getViewRoot() != null)
    		return ResourceBundle.getBundle("LabelMessages",ctx.getViewRoot().getLocale());
        else
        	return ResourceBundle.getBundle("LabelMessages",ctx.getApplication().getViewHandler().calculateLocale(ctx));

	}
	
	@Named
    @Produces       
    public ResourceBundle validateMessageResource(){    	
    	FacesContext ctx = FacesContext.getCurrentInstance();
    	if (ctx.getViewRoot() != null)   
    		return ResourceBundle.getBundle("ValidationMessages",ctx.getViewRoot().getLocale());  
        else  
        	return ResourceBundle.getBundle("ValidationMessages",ctx.getApplication().getViewHandler().calculateLocale(ctx));      	
    }

}
