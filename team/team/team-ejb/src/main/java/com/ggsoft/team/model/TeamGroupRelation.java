package com.ggsoft.team.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class TeamGroupRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="teamGroup")
	private List<Message> messages;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="TeamID")
	private Team team;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="team_group_auth"
		, joinColumns={
			@JoinColumn(name="GroupID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="UserID", nullable=false)
			}
		)
	private List<User> users;

	//bi-directional many-to-one association to TeamGroupAuth
	@OneToMany(mappedBy="teamGroup")
	private List<TeamGroupAuth> teamGroupAuths;
	
	public TeamGroupRelation() {
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<TeamGroupAuth> getTeamGroupAuths() {
		return this.teamGroupAuths;
	}

	public void setTeamGroupAuths(List<TeamGroupAuth> teamGroupAuths) {
		this.teamGroupAuths = teamGroupAuths;
	}

	
}