package org.sitenv.contentvalidator.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CCDAAssignedEntity {
	
	private static Logger log = LogManager.getLogger(CCDAAssignedEntity.class.getName());

	private ArrayList<CCDATelecom>			telecom;
	private ArrayList<CCDAAddress>			addresses;
	private CCDAOrganization				organization;
	
	public void log() {
		
		for(int j = 0; j < telecom.size(); j++) {
			telecom.get(j).log(j);
		}
		
		for(int k = 0; k < addresses.size(); k++) {
			addresses.get(k).log();
		}
		
		if(organization != null)
			organization.log();
	}
	
	public ArrayList<CCDATelecom> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<CCDATelecom> tels) {
		
		if(tels != null)
			this.telecom = tels;
	}

	public ArrayList<CCDAAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<CCDAAddress> addrs) {
		
		if(addrs != null)
			this.addresses = addrs;
	}

	public CCDAOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(CCDAOrganization organization) {
		this.organization = organization;
	}

	public CCDAAssignedEntity()
	{
		telecom = new ArrayList<CCDATelecom>();
		addresses = new ArrayList<CCDAAddress>();
		
	}
}
