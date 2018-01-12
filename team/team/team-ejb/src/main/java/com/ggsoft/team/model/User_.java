package com.ggsoft.team.model;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:44:01.574+0700")
@StaticMetamodel(User.class)
public class User_ extends UserRelation_ {
	public static volatile SingularAttribute<User, Integer> userID;
	public static volatile SingularAttribute<User, Timestamp> lastUpdate;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, String> title;
	public static volatile SingularAttribute<User, Calendar> userLastAccess;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> userStatus;
}
