package com.ggsoft.team.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TeamGroupAuthRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UserID", nullable = false, insertable = false, updatable = false)
	private User user;

	// bi-directional many-to-one association to TeamGroup
	@ManyToOne
	@JoinColumn(name = "GroupID", nullable = false, insertable = false, updatable = false)
	private TeamGroup teamGroup;

	public TeamGroupAuthRelation() {
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TeamGroup getTeamGroup() {
		return this.teamGroup;
	}

	public void setTeamGroup(TeamGroup teamGroup) {
		this.teamGroup = teamGroup;
	}
}