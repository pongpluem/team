package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@Table(name="message")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message extends MessageRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer messageID;

	@Lob
	private String description;

	@Column(nullable=false)
	private Integer groupID;

	@Column(length=120)
	private String imageName;

	private Timestamp lastUpdate;

	@Column(length=120)
	private String location;

	@Column(length=50)
	private String textStyle;

	private Integer titleGroupID;

	private Integer userID;

	public Message() {
	}

	public Integer getMessageID() {
		return this.messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupID() {
		return this.groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTextStyle() {
		return this.textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public Integer getTitleGroupID() {
		return this.titleGroupID;
	}

	public void setTitleGroupID(Integer titleGroupID) {
		this.titleGroupID = titleGroupID;
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}