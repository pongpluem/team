package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the team_group database table.
 * 
 */
@Entity
@Table(name="team_group")
@NamedQuery(name="TeamGroup.findAll", query="SELECT t FROM TeamGroup t")
public class TeamGroup extends TeamGroupRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer groupID;

	private Timestamp createDate;

	@Column(length=120)
	private String description;

	@Column(length=50)
	private String icon;

	@Column(length=50)
	private String iconStyle;

	private Timestamp lastUpdate;

	@Column(length=120)
	private String name;

	@Column(nullable=false)
	private Boolean privateMode;

	private Integer teamID;

	@Column(length=50)
	private String textStyle;

	public TeamGroup() {
	}

	public Integer getGroupID() {
		return this.groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconStyle() {
		return this.iconStyle;
	}

	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
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

	public Boolean getPrivateMode() {
		return this.privateMode;
	}

	public void setPrivateMode(Boolean privateMode) {
		this.privateMode = privateMode;
	}

	public Integer getTeamID() {
		return this.teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}

	public String getTextStyle() {
		return this.textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setTeamGroup(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setTeamGroup(null);

		return message;
	}
	
	public TeamGroupAuth addTeamGroupAuth(TeamGroupAuth teamGroupAuth) {
		getTeamGroupAuths().add(teamGroupAuth);
		teamGroupAuth.setTeamGroup(this);

		return teamGroupAuth;
	}

	public TeamGroupAuth removeTeamGroupAuth(TeamGroupAuth teamGroupAuth) {
		getTeamGroupAuths().remove(teamGroupAuth);
		teamGroupAuth.setTeamGroup(null);

		return teamGroupAuth;
	}
}