package org.sitenv.contentvalidator.model;

import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;

public class CCDAAllergySeverity {
	
	private static Logger log = LogManager.getLogger(CCDAAllergySeverity.class.getName());

	private ArrayList<CCDAII>				templateIds;
	private CCDACode						severity;
	
	private CCDAAuthor		author;
	
	public void log() {
		
		log.info("***Allergy Severity ***");
		
		if(severity != null)
			log.info("Allergy Severity Code = " + severity.getCode());
		
		for(int j = 0; j < templateIds.size(); j++) {
			log.info(" Tempalte Id [" + j + "] = " + templateIds.get(j).getRootValue());
			log.info(" Tempalte Id Ext [" + j + "] = " + templateIds.get(j).getExtValue());
		}
		
		if(author != null)
			author.log();
		
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> ids) {
		
		if(ids != null)
			this.templateIds = ids;
	}

	public CCDACode getSeverity() {
		return severity;
	}

	public void setSeverity(CCDACode severity) {
		this.severity = severity;
	}

	public CCDAAllergySeverity()
	{
		templateIds = new ArrayList<CCDAII>();
	}

	public CCDAAuthor getAuthor() {
		return author;
	}

	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}
	
	
}
