package com.ggsoft.team.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2561-01-12T10:19:37.844+0700")
@StaticMetamodel(Message.class)
public class Message_ extends MessageRelation_ {
	public static volatile SingularAttribute<Message, Integer> messageID;
	public static volatile SingularAttribute<Message, String> description;
	public static volatile SingularAttribute<Message, Integer> groupID;
	public static volatile SingularAttribute<Message, String> imageName;
	public static volatile SingularAttribute<Message, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Message, String> location;
	public static volatile SingularAttribute<Message, String> textStyle;
	public static volatile SingularAttribute<Message, Integer> titleGroupID;
	public static volatile SingularAttribute<Message, Integer> userID;
}
