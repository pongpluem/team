package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-11T14:58:41.928+0700")
@StaticMetamodel(TitleGroup.class)
public class TitleGroup_ extends TitleGroupRelation_ {
	public static volatile SingularAttribute<TitleGroup, Integer> titleGroupID;
	public static volatile SingularAttribute<TitleGroup, String> description;
	public static volatile SingularAttribute<TitleGroup, String> icon;
	public static volatile SingularAttribute<TitleGroup, String> iconStyle;
	public static volatile SingularAttribute<TitleGroup, Timestamp> lastUpdate;
	public static volatile SingularAttribute<TitleGroup, String> name;
	public static volatile SingularAttribute<TitleGroup, String> textStyle;
}
