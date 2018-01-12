package com.ggsoft.team.web.controller;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.ggsoft.team.data.MessageRepository;
import com.ggsoft.team.data.TitleGroupProducer;
import com.ggsoft.team.data.TitleGroupRepository;
import com.ggsoft.team.model.Message;
import com.ggsoft.team.model.TitleGroup;

@Named
@ApplicationScoped
public class TimelineController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private MessageRepository msgRepo;
	
	@Inject
	private TitleGroupRepository titleGroupRepo;
	
	@Inject
	private TitleGroupProducer titleGroupProducer;
	
	private List<Message> listMessages = new ArrayList<>();
	private List<TitleGroup> listTitleGroup = new ArrayList<>();
	
	private StreamedContent graphic;
		
	public TimelineController() {

	}
	
	@PostConstruct
	public void init(){
		log.debug("Begin");
		try {
			//Get All Title Group
			listTitleGroup = titleGroupProducer.geteList();
			
			//Get All Message
			listMessages = msgRepo.retrieveAll(Message.class);
		} catch (Exception e) {			
			log.error(e.getMessage(),e);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", e.getMessage()));
		}
		log.debug("End");
		
	}
	
	public void createContent(){
		log.debug("Begin");
        try {
			BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bufferedImg.createGraphics();
			g2.drawString("This is a text", 0, 10);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bufferedImg, "png", os);
			graphic = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
		} catch (IOException e) {			
			log.error(e.getMessage(),e);
		} 
        log.debug("End");

	}

	public StreamedContent getGraphic() {
		return graphic;
	}

	public void setGraphic(StreamedContent graphic) {
		this.graphic = graphic;
	}

	public List<Message> getListMessages() {
		return listMessages;
	}

	public void setListMessages(List<Message> listMessages) {
		this.listMessages = listMessages;
	}
	

	public StreamedContent imageStream(String fileName){
		
		log.debug("Begin");
      /*  try {
        	InputStream stream = this.getClass().getResourceAsStream(imagesPath+fileName);
        	graphic = new DefaultStreamedContent(stream,"image/png");
		} catch (Exception e) {			
			log.error(e.getMessage(),e);
		} */
		
		/*  try {
				BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = bufferedImg.createGraphics();
				g2.drawString("This is a text", 0, 10);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(bufferedImg, "png", os);
				graphic = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
			} catch (IOException e) {			
				log.error(e.getMessage(),e);
			} */
		
		 FacesContext context = FacesContext.getCurrentInstance();

		    try {
				if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				    // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
				    return new DefaultStreamedContent();
				}
				else {
				    // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
				    //String filename = context.getExternalContext().getRequestParameterMap().get("filename");
				    return new DefaultStreamedContent(new FileInputStream(new File("/msg-images", fileName)));
				}
			} catch (FileNotFoundException e) {
				
				log.error(e.getMessage(),e);
			}
        log.debug("End");
        return graphic;
		
	}
	
	public StreamedContent getImage() throws IOException {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
	        return new DefaultStreamedContent();
	    }
	    else {
	        // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
	        String filename = context.getExternalContext().getRequestParameterMap().get("filename");
	        return new DefaultStreamedContent(new FileInputStream(new File("/path/to/images", filename)));
	    }
	}

	public List<TitleGroup> getListTitleGroup() {
		return listTitleGroup;
	}

	public void setListTitleGroup(List<TitleGroup> listTitleGroup) {
		this.listTitleGroup = listTitleGroup;
	}
	
	public void test(){
		log.debug("Begin");
		log.debug("End");
	}
	
}
