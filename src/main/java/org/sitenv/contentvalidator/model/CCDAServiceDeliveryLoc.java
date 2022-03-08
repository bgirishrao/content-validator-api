package org.sitenv.contentvalidator.model;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CCDAServiceDeliveryLoc {
	
	private static Logger log = LogManager.getLogger(CCDAServiceDeliveryLoc.class.getName());

	private ArrayList<CCDAII>           templateId;
	private CCDACode                    locationCode;
	private ArrayList<CCDAAddress>      address;
	private ArrayList<CCDATelecom>  telecom;
	private CCDADataElement             name;
	
	public void log() {
		
		log.info("*** Service Delivery Location ***");
				
		for(int j = 0; j < templateId.size(); j++) {
			log.info(" Tempalte Id [" + j + "] = " + templateId.get(j).getRootValue());
			log.info(" Tempalte Id Ext [" + j + "] = " + templateId.get(j).getExtValue());
		}
	
		if(locationCode != null)
			log.info("Location Code = " + locationCode.getCode());
		
		for(int i = 0; i < address.size(); i++) {
			address.get(i).log();
		}
		
		for(int l = 0; l < telecom.size(); l++) {
			telecom.get(l).log(l);
		}
		
		if(name != null)
			log.info(" Name = " + name.getValue());
		
	}
	
	public ArrayList<CCDAII> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ArrayList<CCDAII> ids) {
		
		if(ids != null)
			this.templateId = ids;
	}

	public CCDACode getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(CCDACode locationCode) {
		this.locationCode = locationCode;
	}

	public ArrayList<CCDAAddress> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<CCDAAddress> addr) {
		
		if(addr != null)
			this.address = addr;
	}

	public ArrayList<CCDATelecom> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<CCDATelecom> tels) {
		
		if( tels != null)
			this.telecom = tels;
	}

	public CCDADataElement getName() {
		return name;
	}

	public void setName(CCDADataElement name) {
		this.name = name;
	}

	public CCDAServiceDeliveryLoc()
	{
		templateId = new ArrayList<CCDAII>();
		telecom = new ArrayList<CCDATelecom>();
		address = new ArrayList<CCDAAddress>();
	}
}
