package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the team_group_auth database table.
 * 
 */
@Embeddable
public class TeamGroupAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private Integer groupID;

	@Column(unique=true, nullable=false)
	private Integer userID;

	public TeamGroupAuthPK() {
	}
	public Integer getGroupID() {
		return this.groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public Integer getUserID() {
		return this.userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TeamGroupAuthPK)) {
			return false;
		}
		TeamGroupAuthPK castOther = (TeamGroupAuthPK)other;
		return 
			(this.groupID == castOther.groupID)
			&& (this.userID == castOther.userID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupID;
		hash = hash * prime + this.userID;
		
		return hash;
	}
}