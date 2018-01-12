package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the team_auth database table.
 * 
 */
@Embeddable
public class TeamAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private Integer teamID;

	@Column(unique=true, nullable=false)
	private Integer userID;

	public TeamAuthPK() {
	}
	public Integer getTeamID() {
		return this.teamID;
	}
	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
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
		if (!(other instanceof TeamAuthPK)) {
			return false;
		}
		TeamAuthPK castOther = (TeamAuthPK)other;
		return 
			(this.teamID == castOther.teamID)
			&& (this.userID == castOther.userID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.teamID;
		hash = hash * prime + this.userID;
		
		return hash;
	}
}