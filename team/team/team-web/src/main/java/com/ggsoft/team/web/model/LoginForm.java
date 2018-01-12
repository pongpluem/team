package com.ggsoft.team.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Named
@ViewScoped
public class LoginForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private Integer userID;

	@Column(length=200)
	private String password;

	@Column(length=50)
	private String userName;

	public LoginForm() {

	}

	@PostConstruct
	public void init() {
	
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
