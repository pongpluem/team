package com.ggsoft.team.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-11T18:09:22.933+0700")
@StaticMetamodel(TeamGroupRelation.class)
public class TeamGroupRelation_ {
	public static volatile ListAttribute<TeamGroupRelation, Message> messages;
	public static volatile SingularAttribute<TeamGroupRelation, Team> team;
	public static volatile ListAttribute<TeamGroupRelation, User> users;
	public static volatile ListAttribute<TeamGroupRelation, TeamGroupAuth> teamGroupAuths;
}
