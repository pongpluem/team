package com.ggsoft.team.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class UserRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user")
	private List<Message> messages;

	//bi-directional many-to-many association to Team
	@ManyToMany(mappedBy="users")
	private List<Team> teams;

	//bi-directional many-to-one association to TeamAuth
	@OneToMany(mappedBy="user")
	private List<TeamAuth> teamAuths;

	//bi-directional many-to-many association to TeamGroup
	@ManyToMany(mappedBy="users")
	private List<TeamGroup> teamGroups;

	//bi-directional many-to-one association to TeamGroupAuth
	@OneToMany(mappedBy="user")
	private List<TeamGroupAuth> teamGroupAuths;


	public UserRelation() {
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
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

	public List<TeamGroupAuth> getTeamGroupAuths() {
		return this.teamGroupAuths;
	}

	public void setTeamGroupAuths(List<TeamGroupAuth> teamGroupAuths) {
		this.teamGroupAuths = teamGroupAuths;
	}


}