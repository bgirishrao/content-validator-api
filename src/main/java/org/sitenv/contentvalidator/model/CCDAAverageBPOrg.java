package org.sitenv.contentvalidator.model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCDAAverageBPOrg {

	private static Logger log = LoggerFactory.getLogger(CCDAAverageBPOrg.class.getName());

	private ArrayList<CCDAII>   			templateIds;
	private CCDACode						statusCode;
	private CCDACode						orgCode;
	private CCDACode						translationCode;
	private CCDAEffTime						effTime;
	private ArrayList<CCDAVitalObs>			vitalObs;
	
	private CCDAAuthor author;
	
	public void log() {
		
		if(orgCode != null)
			log.info(" Organizer  Code = " + orgCode.getCode());
		
		if(translationCode != null)
			log.info(" Translation  Code = " + translationCode.getCode());
		
		if(statusCode != null)
			log.info(" Organizer Status  Code = " + statusCode.getCode());
		
		if(effTime != null)
			effTime.log();
		
		for(int j = 0; j < templateIds.size(); j++) {
			log.info(" Tempalte Id [" + j + "] = " + templateIds.get(j).getRootValue());
			log.info(" Tempalte Id Ext [" + j + "] = " + templateIds.get(j).getExtValue());
		}
		
		for(int k = 0; k < vitalObs.size(); k++) {
			vitalObs.get(k).log();
		}
		
		if(author != null)
			author.log();
	}
	
	public CCDAAverageBPOrg()
	{
		templateIds = new ArrayList<CCDAII>();
		vitalObs = new ArrayList<CCDAVitalObs>();
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> ids) {
		
		if(ids != null)
			this.templateIds = ids;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDACode getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(CCDACode orgCode) {
		this.orgCode = orgCode;
	}

	public CCDACode getTranslationCode() {
		return translationCode;
	}

	public void setTranslationCode(CCDACode translationCode) {
		this.translationCode = translationCode;
	}

	public CCDAEffTime getEffTime() {
		return effTime;
	}

	public void setEffTime(CCDAEffTime effTime) {
		this.effTime = effTime;
	}

	public ArrayList<CCDAVitalObs> getVitalObs() {
		return vitalObs;
	}

	public void setVitalObs(ArrayList<CCDAVitalObs> vobs) {
		
		if(vobs != null)
			this.vitalObs = vobs;
	}

	public CCDAAuthor getAuthor() {
		return author;
	}

	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}
	
	
}
