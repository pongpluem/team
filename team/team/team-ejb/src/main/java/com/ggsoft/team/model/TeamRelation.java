package com.ggsoft.team.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class TeamRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-many association to User
		@ManyToMany
		@JoinTable(
			name="team_auth"
			, joinColumns={
				@JoinColumn(name="TeamID", nullable=false)
				}
			, inverseJoinColumns={
				@JoinColumn(name="UserID", nullable=false)
				}
			)
		private List<User> users;

		//bi-directional many-to-one association to TeamAuth
		@OneToMany(mappedBy="team")
		private List<TeamAuth> teamAuths;

		//bi-directional many-to-one association to TeamGroup
		@OneToMany(mappedBy="team")
		private List<TeamGroup> teamGroups;
		
	public TeamRelation() {
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<TeamAuth> getTeamAuths() {
		return this.teamAuths;
	}

	public void setTeamAuths(List<TeamAuth> teamAuths) {
		this.teamAuths = teamAuths;
	}
	
	public List<TeamGroup> getTeamGroups() {
		return this.teamGroups;
	}

	public void setTeamGroups(List<TeamGroup> teamGroups) {
		this.teamGroups = teamGroups;
	}	

}