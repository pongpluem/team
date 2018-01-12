package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the team_auth database table.
 * 
 */
@Entity
@Table(name="team_auth")
@NamedQuery(name="TeamAuth.findAll", query="SELECT t FROM TeamAuth t")
public class TeamAuth extends TeamAuthRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeamAuthPK id;

	@Column(nullable=false)
	private Boolean create;

	@Column(nullable=false)
	private Boolean delete;

	@Column(nullable=false, length=120)
	private String description;

	@Column(nullable=false)
	private Timestamp lastUpdate;

	@Column(nullable=false)
	private Boolean read;

	@Column(nullable=false)
	private Boolean update;

	public TeamAuth() {
	}

	public TeamAuthPK getId() {
		return this.id;
	}

	public void setId(TeamAuthPK id) {
		this.id = id;
	}

	public Boolean getCreate() {
		return this.create;
	}

	public void setCreate(Boolean create) {
		this.create = create;
	}

	public Boolean getDelete() {
		return this.delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getUpdate() {
		return this.update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

}