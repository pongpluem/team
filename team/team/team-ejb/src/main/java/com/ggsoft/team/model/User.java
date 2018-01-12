package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Calendar;
import java.sql.Timestamp;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends UserRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer userID;

	private Timestamp lastUpdate;

	@Column(length=120)
	private String name;

	@Column(length=200)
	private String password;

	@Column(length=120)
	private String surname;

	@Column(length=50)
	private String title;

	@Temporal(TemporalType.DATE)
	private Calendar userLastAccess;

	@Column(length=50)
	private String userName;

	@Column(length=1)
	private String userStatus;

	public User() {
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getUserLastAccess() {
		return this.userLastAccess;
	}

	public void setUserLastAccess(Calendar userLastAccess) {
		this.userLastAccess = userLastAccess;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public TeamGroupAuth addTeamGroupAuth(TeamGroupAuth teamGroupAuth) {
		getTeamGroupAuths().add(teamGroupAuth);
		teamGroupAuth.setUser(this);

		return teamGroupAuth;
	}

	public TeamGroupAuth removeTeamGroupAuth(TeamGroupAuth teamGroupAuth) {
		getTeamGroupAuths().remove(teamGroupAuth);
		teamGroupAuth.setUser(null);

		return teamGroupAuth;
	}
	
	public TeamAuth addTeamAuth(TeamAuth teamAuth) {
		getTeamAuths().add(teamAuth);
		teamAuth.setUser(this);

		return teamAuth;
	}

	public TeamAuth removeTeamAuth(TeamAuth teamAuth) {
		getTeamAuths().remove(teamAuth);
		teamAuth.setUser(null);

		return teamAuth;
	}
	
	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setUser(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setUser(null);

		return message;
	}

}