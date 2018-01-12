package com.ggsoft.team.web.controller;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SampleController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public SampleController() {

	}
	
	@PostConstruct
	public void init(){
		
	}

}
