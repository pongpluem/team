package com.ggsoft.team.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class TitleGroupRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;	

	public TitleGroupRelation() {
	}
	
}