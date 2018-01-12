package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:21:29.790+0700")
@StaticMetamodel(Team.class)
public class Team_ extends TeamRelation_ {
	public static volatile SingularAttribute<Team, Integer> teamID;
	public static volatile SingularAttribute<Team, Timestamp> createDate;
	public static volatile SingularAttribute<Team, String> description;
	public static volatile SingularAttribute<Team, String> icon;
	public static volatile SingularAttribute<Team, String> iconStyle;
	public static volatile SingularAttribute<Team, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Team, String> name;
	public static volatile SingularAttribute<Team, Boolean> privateMode;
	public static volatile SingularAttribute<Team, String> textStyle;
}
