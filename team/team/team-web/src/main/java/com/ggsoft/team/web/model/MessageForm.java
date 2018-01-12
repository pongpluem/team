package com.ggsoft.team.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Column;


@Named
@ViewScoped
public class MessageForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private Integer messageID;

	
	private String description;

	
	private String imageName;

	private Timestamp lastUpdate;

	
	private String location;

	
	private String textStyle;

	private Integer titleGroupID;

	private Integer userID;

	public MessageForm() {

	}

	@PostConstruct
	public void init() {
	
	}

	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public Integer getTitleGroupID() {
		return titleGroupID;
	}

	public void setTitleGroupID(Integer titleGroupID) {
		this.titleGroupID = titleGroupID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	
}
