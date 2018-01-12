package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the title_group database table.
 * 
 */
@Entity
@Table(name="title_group")
@NamedQuery(name="TitleGroup.findAll", query="SELECT t FROM TitleGroup t")
public class TitleGroup extends TitleGroupRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer titleGroupID;

	@Column(length=120)
	private String description;

	@Column(length=50)
	private String icon;

	@Column(length=50)
	private String iconStyle;

	private Timestamp lastUpdate;

	@Column(length=120)
	private String name;

	@Column(length=50)
	private String textStyle;

	public TitleGroup() {
	}

	public Integer getTitleGroupID() {
		return this.titleGroupID;
	}

	public void setTitleGroupID(Integer titleGroupID) {
		this.titleGroupID = titleGroupID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconStyle() {
		return this.iconStyle;
	}

	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextStyle() {
		return this.textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

}