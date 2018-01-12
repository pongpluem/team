package com.ggsoft.team.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TeamAuthRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="TeamID", nullable=false, insertable=false, updatable=false)
	private Team team;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID", nullable=false, insertable=false, updatable=false)
	private User user;

	public TeamAuthRelation() {
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}