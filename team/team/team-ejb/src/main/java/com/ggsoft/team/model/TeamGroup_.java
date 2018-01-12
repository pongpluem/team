package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:25:42.408+0700")
@StaticMetamodel(TeamGroup.class)
public class TeamGroup_ extends TeamGroupRelation_ {
	public static volatile SingularAttribute<TeamGroup, Integer> groupID;
	public static volatile SingularAttribute<TeamGroup, Timestamp> createDate;
	public static volatile SingularAttribute<TeamGroup, String> description;
	public static volatile SingularAttribute<TeamGroup, String> icon;
	public static volatile SingularAttribute<TeamGroup, String> iconStyle;
	public static volatile SingularAttribute<TeamGroup, Timestamp> lastUpdate;
	public static volatile SingularAttribute<TeamGroup, String> name;
	public static volatile SingularAttribute<TeamGroup, Boolean> privateMode;
	public static volatile SingularAttribute<TeamGroup, Integer> teamID;
	public static volatile SingularAttribute<TeamGroup, String> textStyle;
}
