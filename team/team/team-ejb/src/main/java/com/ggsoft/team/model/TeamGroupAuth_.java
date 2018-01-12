package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:41:03.562+0700")
@StaticMetamodel(TeamGroupAuth.class)
public class TeamGroupAuth_ extends TeamGroupAuthRelation_ {
	public static volatile SingularAttribute<TeamGroupAuth, TeamGroupAuthPK> id;
	public static volatile SingularAttribute<TeamGroupAuth, Boolean> create;
	public static volatile SingularAttribute<TeamGroupAuth, Boolean> delete;
	public static volatile SingularAttribute<TeamGroupAuth, String> description;
	public static volatile SingularAttribute<TeamGroupAuth, Timestamp> lastUpdate;
	public static volatile SingularAttribute<TeamGroupAuth, Boolean> read;
	public static volatile SingularAttribute<TeamGroupAuth, Boolean> update;
}
