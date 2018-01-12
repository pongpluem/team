package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:23:57.803+0700")
@StaticMetamodel(TeamAuth.class)
public class TeamAuth_ extends TeamAuthRelation_ {
	public static volatile SingularAttribute<TeamAuth, TeamAuthPK> id;
	public static volatile SingularAttribute<TeamAuth, Boolean> create;
	public static volatile SingularAttribute<TeamAuth, Boolean> delete;
	public static volatile SingularAttribute<TeamAuth, String> description;
	public static volatile SingularAttribute<TeamAuth, Timestamp> lastUpdate;
	public static volatile SingularAttribute<TeamAuth, Boolean> read;
	public static volatile SingularAttribute<TeamAuth, Boolean> update;
}
