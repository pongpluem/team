package com.ggsoft.team.data;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.ggsoft.team.model.Message;


@Named
@ApplicationScoped
public class AccountRepository extends Repository implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public Message findById(Integer id) {
		return em.find(Message.class, id);
	}

}
