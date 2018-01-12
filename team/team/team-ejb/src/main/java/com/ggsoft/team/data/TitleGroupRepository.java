package com.ggsoft.team.data;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.ggsoft.team.model.TitleGroup;


@Named
@ApplicationScoped
public class TitleGroupRepository extends Repository implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public TitleGroup findById(Integer id) {
		return em.find(TitleGroup.class, id);
	}

}
