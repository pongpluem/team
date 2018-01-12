package com.ggsoft.team.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;

import com.ggsoft.team.model.Message;
import com.ggsoft.team.model.TitleGroup;



@Named
@ApplicationScoped
public class TitleGroupProducer  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Named("produceLog")
	private Logger log;
	
	
	@Inject
	private TitleGroupRepository repository;

	private List<TitleGroup> eList = new ArrayList<>();
		
	
	public TitleGroupProducer() {	
	}
	@PostConstruct
	public void init(){
		findAll();
	}
		
	public void findAll() {
		try {		
			log.debug("Begin");			
			seteList(repository.retrieveAll(TitleGroup.class));	
			log.debug("End");
		} catch (Exception e) {
			log.debug("Find All failed.", e);
		}
	}
	
	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TitleGroup obj) {
		log.debug("Begin");
		findAll();
        log.debug("End");
    }
	public List<TitleGroup> geteList() {
		return eList;
	}
	public void seteList(List<TitleGroup> eList) {
		this.eList = eList;
	}
			    

}
