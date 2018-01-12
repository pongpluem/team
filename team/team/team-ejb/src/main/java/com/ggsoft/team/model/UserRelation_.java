package com.ggsoft.team.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:43:07.738+0700")
@StaticMetamodel(UserRelation.class)
public class UserRelation_ {
	public static volatile ListAttribute<UserRelation, Message> messages;
	public static volatile ListAttribute<UserRelation, Team> teams;
	public static volatile ListAttribute<UserRelation, TeamAuth> teamAuths;
	public static volatile ListAttribute<UserRelation, TeamGroup> teamGroups;
	public static volatile ListAttribute<UserRelation, TeamGroupAuth> teamGroupAuths;
}
