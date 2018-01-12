package com.ggsoft.team.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:20:13.069+0700")
@StaticMetamodel(TeamRelation.class)
public class TeamRelation_ {
	public static volatile ListAttribute<TeamRelation, User> users;
	public static volatile ListAttribute<TeamRelation, TeamAuth> teamAuths;
	public static volatile ListAttribute<TeamRelation, TeamGroup> teamGroups;
}
