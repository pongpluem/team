package com.ggsoft.team.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.logging.log4j.Logger;
import org.primefaces.model.UploadedFile;

import com.ggsoft.team.model.User;
import com.ggsoft.team.web.util.SystemProperty;
import com.ggsoft.team.web.util.WebResources;

@Named
@SessionScoped
public class BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesContext ctx;

	//Upload
	@Inject
    @SystemProperty("imagesPath")
	protected String destination;  //D:/logs/upload/
	
	@Inject
    @SystemProperty("imagesPath")
	protected String imagesPath;  //D:/logs/upload/
	
	@Inject
    @SystemProperty("tempPath")
	protected String tempDestination;
		
	@Inject 
	WebResources webResource;
	
	@Inject
	@Named("produceWebLog")
	protected Logger log;
	
	@Inject
	protected UserData userData;
	
	protected  static ResourceBundle resource;
	
	protected UploadedFile uploadFile;
	protected String text;
	protected String fileName;
		

	public BaseController() {
		
	}
	
	@PostConstruct
	public void init(){
		resource=ResourceBundle.getBundle("com.config.message",ctx.getViewRoot().getLocale());
	}

	public void deleteOriginalFile(String path)throws Exception{
		File file = new File(path);
		try {
			Files.delete(file.toPath());
		} catch (NoSuchFileException e) {
			log.error(e.getMessage(), e);
		} catch (DirectoryNotEmptyException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
		    // File permission problems are caught here.
			log.error(e.getMessage(), e);
		}
	}

	//Write CSV file to directory(Export)

	public void uploadFileToDirectory(String screenId, boolean Temp)throws Exception{
		try{
			if(Temp){
				//Get folder destination
				destination = tempDestination;

				//Get fileName
	    		fileName = new String(uploadFile.getFileName().getBytes("ISO-8859-1"),"UTF-8");
//	    		fileName = uploadFile.getFileName();
			}else{
				//Get folder destination
				//destination = webResource.createProp("uploadPath");
				Properties prop = new Properties();
				prop.load(this.getClass().getClassLoader().getResourceAsStream("/com/config/ccm_config.properties"));
				destination += screenId!=""?screenId+prop.getProperty("path_separator"):"Unknow"+prop.getProperty("path_separator");
				destination += LocalDate.now().getYear()+String.format("%02d",LocalDate.now().getMonthValue())+""+prop.getProperty("path_separator")
						+"D"+String.format("%02d", LocalDate.now().getDayOfMonth())+""+prop.getProperty("path_separator");
			}

	    	log.debug("destination :"+destination);
    		log.debug("fileName :"+fileName);

    		//Copy file to directory.
            if(null != uploadFile){
            	copyFileToDirectoryUTF8(Temp);
            }
		}
		catch(IOException e){
			log.error("log.error.io",resource,e.getMessage(),e.getCause());

			MessageFormat mf = new MessageFormat(getString("log.upload.directory.notfound"));
			Object[] objArray = {fileName};
			String fileMessage = mf.format(objArray);

            throw new java.io.IOException(fileMessage+" ("+e.getMessage()+")");
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}

	public void copyFileToDirectoryUTF8(boolean Temp) throws Exception {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmm_", new Locale("th", "TH"));
    	Date date = new Date();
    	try {
             //Write the inputStream to a FileOutputStream
        	 File theDir = new File(destination);
        	 if(!theDir.exists()){
        		 theDir.mkdirs();
        	 }

        	 if(Temp)
        		 //Ex. 31122560_1130_eMachineBinary
        		 fileName = sf.format(date)+fileName;

             OutputStream out = new FileOutputStream(new File(destination + fileName));
             Writer w = new OutputStreamWriter(out, "UTF8");

             InputStream in = uploadFile.getInputstream();//.getInputStream();

             BufferedReader br=new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8));

             String line;
             while ((line = br.readLine()) != null) {
 				w.write(line+"\r\n");
 			}

             w.flush();
             w.close();
             br.close();
             in.close();
             out.close();

             log.debug("{} file created!",fileName);
        } catch (IOException e) {
        	log.error("log.error.io",resource,e.getMessage(),e.getCause());
             throw new java.io.IOException(e);
        }catch(Exception e){
        	log.error(e.getMessage(), e);
        }
    }

	public static String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");

                fileName=fileName.substring(fileName.lastIndexOf("\\")+1 );
                return fileName;
            }
        }
        return null;
    }
	
	
	protected String getString(String key){
		return resource.getString(key);
	}

	protected String getImagesPath() {
		return imagesPath;
	}
	
	protected User getUserData(){
		return userData.getUser();
	}
	
}
