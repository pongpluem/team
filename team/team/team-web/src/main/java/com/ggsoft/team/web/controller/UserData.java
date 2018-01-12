package com.ggsoft.team.web.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;
import org.primefaces.model.menu.MenuModel;

import com.ggsoft.team.data.UserRepository;
import com.ggsoft.team.model.User;
import com.ggsoft.team.web.model.LoginForm;


@Named
@SessionScoped
public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named("produceWebLog")
	protected Logger log;
	
	@Inject
	@Named("appConfigResource")
	transient ResourceBundle appConfigRes;
	
	@Inject
	@Named("labelMessageResource")
	transient ResourceBundle labelMessageRes;
	
	@Inject
	private UserRepository userRepo;
	
	@Inject
	private LoginForm loginForm;
	
	
	private static Map<String, Object> countries;
	private String locale = "en";
	private Locale localeSession=null;
	private MenuModel model;


	private User user;
	
	public UserData(){	
	}

	@PostConstruct
	public void init() {
		log.debug("Begin");
		countries = new LinkedHashMap<String, Object>();
		Locale localTh = new Locale("th", "TH", "Thailand");
		countries.put("Thai", localTh);
		countries.put("English", Locale.ENGLISH);
		try{
			String  default_locale = appConfigRes.getString("default_locale");
			if(localTh.getLanguage().equals(default_locale)){

				//Set Default locale
				setLocaleSession(localTh);
				setLocale(localTh.toString());
				FacesContext.getCurrentInstance().getViewRoot().setLocale(localTh);
				//resource=ResourceBundle.getBundle("message",localTh);
			}else{
				setLocaleSession(Locale.ENGLISH);
				FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
				//resource=ResourceBundle.getBundle("message",Locale.ENGLISH);
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		
		log.debug("End");

	}
	
	public Map<String, Object> getCountries() {
		return countries;
	}


	// value change event listener
	public void localeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
				setLocale(newLocaleValue);
				setLocaleSession((Locale) entry.getValue());				
			}
		}
	}

	public Boolean validateLogin(){
		user = null;
		try {
			user = userRepo.Login(loginForm.getUserID(), loginForm.getUserName(),loginForm.getPassword());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		if(user != null)
			return true;
		else
			return false;
	}
	
	public void updateLogin(){
		log.debug("Begin");
	}
	
	public void updateLogout(){
		log.debug("Begin");
	}

	public String login(){
		log.debug("Begin");
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			//boolean check=validateLogin();
			boolean check;
			user = userRepo.Login(loginForm.getUserID(), loginForm.getUserName(), loginForm.getPassword());
			if(user != null)
				check = true;
			else
				check = false;
			//boolean checkLDAP=true;
			//if(check){				
				if (user != null){
					String md5EnCrypt = userRepo.md5EnCrypt(loginForm.getPassword());
					if(Optional.ofNullable(user.getPassword()).orElse("").equals(md5EnCrypt)){
					if (user.getUserStatus().equals("A"))
					{								
						//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constant.SESSION_MAP_AUTH, mapAuths);						
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userid",user.getUserID());

						//log.info("log.user.signin",resource,userName);
						updateLogin();
						log.debug("End");
						return "dashboard?faces-redirect=true";					
					}
					else
					{
						//log.error(e.getMessage(),getClass().getName(),e.getKey());
						log.debug("User Sign In Disabled :"+loginForm.getUserName());
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Unsuccessful !!","Your Account is disabled" ));
					}
					}
					else{
						log.debug("Invalid Password :"+loginForm.getUserName());
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Unsuccessful !!","Invalid Password" ));
					}
				}
				else
				{
					log.debug("User not found :"+loginForm.getUserName());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Unsuccessful !!","Your Account is not found" ));
				}

			//}
		}catch(MissingResourceException e){
			log.debug(e.getMessage(),getClass().getName(),e.getKey());
			log.debug("User Sign In Fail :"+loginForm.getUserName());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"MissingResourceException !!","" ));
		}catch(Exception e){
			log.debug(e.toString(), e);
			log.debug("User Sign In Fail :"+loginForm.getUserName());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Exception!!","" ));
		}
		log.debug("End");
		return null;

	}

	public void logout() throws Exception {
		updateLogout();
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();	    
	    ec.invalidateSession();
	    ec.redirect("/ccm-web/?faces-redirect=true&logoutMsg=true");
	}
	
	
	public MenuModel getModel() { return model; }


	public Locale getLocaleSession() {
		return localeSession;
	}

	public void setLocaleSession(Locale localeSession) {
		this.localeSession = localeSession;
	}

	public void showLocale(){
		FacesContext.getCurrentInstance().getViewRoot().setLocale(localeSession);
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
