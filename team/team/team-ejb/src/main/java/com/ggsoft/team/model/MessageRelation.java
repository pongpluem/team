package com.ggsoft.team.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MessageRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to TitleGroup
	@ManyToOne
	@JoinColumn(name = "TitleGroupID")
	private TitleGroup titlegroup;

	//bi-directional many-to-one association to TeamGroup
		@ManyToOne
		@JoinColumn(name="GroupID", nullable=false)
		private TeamGroup teamGroup;

		//bi-directional many-to-one association to User
		@ManyToOne
		@JoinColumn(name="UserID")
		private User user;

	public MessageRelation() {
	}

	public TitleGroup getTitlegroup() {
		return this.titlegroup;
	}

	public void setTitlegroup(TitleGroup titlegroup) {
		this.titlegroup = titlegroup;
	}

	public User getUser() {
		return user;
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