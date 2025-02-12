package org.sitenv.contentvalidator.model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthConcernAct {

private static Logger log = LoggerFactory.getLogger(HealthConcernAct.class.getName());
    
	private ArrayList<CCDAII>     templateIds;
	private CCDACode						healthConcernActCode;
	private CCDACode						statusCode;
	private CCDAEffTime						effectiveTime;
	private ArrayList<CCDAProblemObs>	 	problemObservations;
	
	private CCDAAuthor	author;
	
	public HealthConcernAct() {
		
		templateIds = new ArrayList<>();
		problemObservations = new ArrayList<>();
	}
	
	public void log() {
		
		if(healthConcernActCode != null)
			log.info(" Health Concern Act  Code = " + healthConcernActCode.getCode());
		
		if(statusCode != null)
			log.info(" Lab Result Status  Code = " + statusCode.getCode());
		
		if(effectiveTime != null)
			effectiveTime.log();
		
		for(int j = 0; j < templateIds.size(); j++) {
			log.info(" Tempalte Id [" + j + "] = " + templateIds.get(j).getRootValue());
			log.info(" Tempalte Id Ext [" + j + "] = " + templateIds.get(j).getExtValue());
		}
		
		for(int k = 0; k < templateIds.size(); k++) {
			problemObservations.get(k).log();
		}
		
		if(author != null)
			author.log();
	}

	public ArrayList<CCDAII> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(ArrayList<CCDAII> templateIds) {
		this.templateIds = templateIds;
	}

	public CCDACode getHealthConcernActCode() {
		return healthConcernActCode;
	}

	public void setHealthConcernActCode(CCDACode healthConcernActCode) {
		this.healthConcernActCode = healthConcernActCode;
	}

	public CCDACode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CCDACode statusCode) {
		this.statusCode = statusCode;
	}

	public CCDAEffTime getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(CCDAEffTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public ArrayList<CCDAProblemObs> getProblemObservations() {
		return problemObservations;
	}

	public void setProblemObservations(ArrayList<CCDAProblemObs> problemObservations) {
		this.problemObservations = problemObservations;
	}

	public CCDAAuthor getAuthor() {
		return author;
	}

	public void setAuthor(CCDAAuthor author) {
		this.author = author;
	}
	
	
}
