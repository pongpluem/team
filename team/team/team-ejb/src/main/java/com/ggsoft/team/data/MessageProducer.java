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



@Named
@ApplicationScoped
public class MessageProducer  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Named("produceLog")
	private Logger log;
	
	
	@Inject
	private MessageRepository repository;

	private List<Message> eList = new ArrayList<>();
		
	
	public MessageProducer() {	
	}
	@PostConstruct
	public void init(){
		retrieveAll();
	}
		
	public void retrieveAll() {
		try {		
			log.debug("Begin...");			
			seteList(repository.retrieveAll(Message.class));	
			log.debug("End...");
		} catch (Exception e) {
			log.debug("Retrieve failed.", e);
		}
	}
	
	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Message obj) {
		log.debug("Begin...");
        retrieveAll();
        log.debug("End...");
    }
	public List<Message> geteList() {
		return eList;
	}
	public void seteList(List<Message> eList) {
		this.eList = eList;
	}
			    

}
