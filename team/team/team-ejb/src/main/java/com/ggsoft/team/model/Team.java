package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@Table(name="team")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team extends TeamRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer teamID;

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

	@Column(length=50)
	private String textStyle;

	public Team() {
	}

	public Integer getTeamID() {
		return this.teamID;
	}

	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
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

	public String getTextStyle() {
		return this.textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}
	
	public TeamGroup addTeamGroup(TeamGroup teamGroup) {
		getTeamGroups().add(teamGroup);
		teamGroup.setTeam(this);

		return teamGroup;
	}

	public TeamGroup removeTeamGroup(TeamGroup teamGroup) {
		getTeamGroups().remove(teamGroup);
		teamGroup.setTeam(null);

		return teamGroup;
	}
	
	public TeamAuth addTeamAuth(TeamAuth teamAuth) {
		getTeamAuths().add(teamAuth);
		teamAuth.setTeam(this);

		return teamAuth;
	}

	public TeamAuth removeTeamAuth(TeamAuth teamAuth) {
		getTeamAuths().remove(teamAuth);
		teamAuth.setTeam(null);

		return teamAuth;
	}

}