package org.sitenv.contentvalidator.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CCDAHealthConcerns {

	private static Logger log = LogManager.getLogger(CCDAHealthConcerns.class.getName());
    
	private ArrayList<CCDAII>     templateIds;
	
	private CCDAAuthor	author;
	
	public CCDAHealthConcerns() {
		templateIds = new ArrayList<CCDAII>();
	}
	
	
	
	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}



	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}



	public CCDAAuthor getAuthor() {
		return author;
	}



	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}



	public void log() { 
		
		for(int j = 0; j < templateIds.size(); j++) {
			log.info(" Tempalte Id [" + j + "] = " + templateIds.get(j).getRootValue());
			log.info(" Tempalte Id Ext [" + j + "] = " + templateIds.get(j).getExtValue());
		}	
		
		if(author != null)
			author.log();
	}
}
