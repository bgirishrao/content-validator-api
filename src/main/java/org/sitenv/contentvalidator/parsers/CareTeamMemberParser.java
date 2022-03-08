package org.sitenv.contentvalidator.parsers;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sitenv.contentvalidator.model.CCDACareTeamMember;
import org.sitenv.contentvalidator.model.CCDACareTeamMemberAct;
import org.sitenv.contentvalidator.model.CCDAParticipant;
import org.sitenv.contentvalidator.model.CCDARefModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CareTeamMemberParser {
	
	private static Logger log = LogManager.getLogger(CareTeamMemberParser.class.getName());
	
    public static void parse(Document doc, CCDARefModel model, boolean curesUpdate) throws XPathExpressionException {
    	
    	log.info(" *** Parsing Care Team Members *** ");
    	model.setMembers(retrieveCTMDetails(doc));	
	}
    
    public static void parseCareTeamSection(Document doc, CCDARefModel model, boolean curesUpdate) throws XPathExpressionException {
    	
    	log.info(" *** Parsing Care Team Section *** ");
    	model.setCareTeamSectionMembers(retrieveCareTeamSectionDetails(doc));	
	}
    
    public static CCDACareTeamMember retrieveCareTeamSectionDetails(Document doc) throws XPathExpressionException 
    {
    	CCDACareTeamMember careTeamMember = null;
		Element sectionElement = (Element) CCDAConstants.CARE_TEAM_SECTION_EXPRESSION.evaluate(doc, XPathConstants.NODE);
		
		if(sectionElement != null)
		{
			log.info(" Found Care Team Member section ");
			careTeamMember = new CCDACareTeamMember();
			careTeamMember.setSectionTemplateId(ParserUtilities.readTemplateIdList((NodeList) CCDAConstants.REL_TEMPLATE_ID_EXP.
													evaluate(sectionElement, XPathConstants.NODESET)));
			
			careTeamMember.setSectionCode(ParserUtilities.readCode((Element) CCDAConstants.REL_CODE_EXP.
						evaluate(sectionElement, XPathConstants.NODE)));
			
			// Add Member Acts
			careTeamMember.setMemberActs(readMemberActs((NodeList) CCDAConstants.REL_CARE_TEAM_ORG_EXPRESSION.
									evaluate(sectionElement, XPathConstants.NODESET)));			
			
			careTeamMember.setAuthor(ParserUtilities.readAuthor((Element) CCDAConstants.REL_AUTHOR_EXP.
					evaluate(sectionElement, XPathConstants.NODE)));
		}
		
    	return careTeamMember;
    }
    
    public static ArrayList<CCDACareTeamMemberAct> readMemberActs(NodeList orgList) throws XPathExpressionException
    {
    	ArrayList<CCDACareTeamMemberAct> careTeamMembers = new ArrayList<>();

    	if(orgList != null) {
    		
			for (int i = 0; i < orgList.getLength(); i++) {
				
				log.info("Found Organizer ");
				
				Element orgElement = (Element) orgList.item(i);
				
				// Parse the Members
				retrieveMembers((NodeList) CCDAConstants.REL_CARE_TEAM_MEMBER_ACT_EXPRESSION.
						evaluate(orgElement, XPathConstants.NODESET), careTeamMembers);
				
			}
    	}
		return careTeamMembers;
    }
    
    public static void retrieveMembers(NodeList memberActNodes, ArrayList<CCDACareTeamMemberAct> careTeamMembers) throws XPathExpressionException
    {
    	if(memberActNodes != null)
    	{
			for (int i = 0; i < memberActNodes.getLength(); i++) {
				
				log.info(" Found Member Act Node ");
				CCDACareTeamMemberAct mact = new CCDACareTeamMemberAct();
				
				Element memberActElement = (Element) memberActNodes.item(i);
				
				mact.setTemplateIds(ParserUtilities.readTemplateIdList((NodeList) CCDAConstants.REL_TEMPLATE_ID_EXP.
						evaluate(memberActElement, XPathConstants.NODESET)));
				
				mact.setMemberActCode(ParserUtilities.readCode((Element) CCDAConstants.REL_CODE_EXP.
						evaluate(memberActElement, XPathConstants.NODE)));
				
				mact.setStatusCode(ParserUtilities.readCode((Element) CCDAConstants.REL_STATUS_CODE_EXP.
						evaluate(memberActElement, XPathConstants.NODE)));
				
				mact.setEffectiveTime(ParserUtilities.readEffectiveTime((Element) CCDAConstants.REL_EFF_TIME_EXP.
						evaluate(memberActElement, XPathConstants.NODE)));
				
				mact.setAuthor(ParserUtilities.readAuthor((Element) CCDAConstants.REL_AUTHOR_EXP.
						evaluate(memberActElement, XPathConstants.NODE)));
				
				Element performerElement = (Element)CCDAConstants.REL_PERFORMER_EXP.evaluate(memberActElement,XPathConstants.NODE);
				
				if(performerElement != null)
				{
					log.info(" Found Perfomer ");
					CCDAParticipant pp = new CCDAParticipant();
					readName((Element) CCDAConstants.REL_ASSN_ENTITY_PERSON_NAME.
		    				evaluate(performerElement, XPathConstants.NODE), pp);
					
					mact.setPrimaryPerformer(pp);
				}
				
				NodeList participantNodeList = (NodeList)CCDAConstants.REL_PARTICIPANT_EXP.evaluate(memberActElement,XPathConstants.NODESET);
				
				if(participantNodeList != null)
				{
					for(int j = 0; j < participantNodeList.getLength(); j++)
					{
						log.info(" Found Participants ");
						CCDAParticipant opp = new CCDAParticipant();
						readName((Element) CCDAConstants.REL_ASSN_ENTITY_PERSON_NAME.
			    				evaluate(performerElement, XPathConstants.NODE), opp);
						
						mact.addParticipant(opp);
					}		
				}
				
				careTeamMembers.add(mact);
			}
    	}
			
    }
	
	public static CCDACareTeamMember retrieveCTMDetails(Document doc) throws XPathExpressionException
	{
		NodeList performerNodeList = (NodeList) CCDAConstants.CARE_TEAM_EXPRESSION.evaluate(doc, XPathConstants.NODESET);
		CCDACareTeamMember careTeamMember = new CCDACareTeamMember();
		ArrayList<CCDAParticipant> participantList = new ArrayList<>();
		CCDAParticipant participant ;
		for (int i = 0; i < performerNodeList.getLength(); i++) {
			
			log.info("Creating PArticipant");
			participant = new CCDAParticipant();
			Element performerElement = (Element) performerNodeList.item(i);
			
			participant.setAddress(ParserUtilities.readAddress((Element) CCDAConstants.REL_ASSN_ENTITY_ADDR.
					evaluate(performerElement, XPathConstants.NODE)));
			
			readName((Element) CCDAConstants.REL_ASSN_ENTITY_PERSON_NAME.
	    				evaluate(performerElement, XPathConstants.NODE), participant);
			
			participant.setTelecom(ParserUtilities.readTelecom((Element) CCDAConstants.REL_ASSN_ENTITY_TEL_EXP.
					evaluate(performerElement, XPathConstants.NODE)));
			participantList.add(participant);
		}
		careTeamMember.setMembers(participantList);
		
		return careTeamMember;
	}
	
	public static void readName(Element nameElement,CCDAParticipant participant) throws XPathExpressionException
	{
		if(nameElement != null)
		{
			NodeList giveNameNodeList = (NodeList) CCDAConstants.REL_GIVEN_NAME_EXP.
					evaluate(nameElement, XPathConstants.NODESET);
			
			for (int i = 0; i < giveNameNodeList.getLength(); i++) {
				Element givenNameElement = (Element) giveNameNodeList.item(i);
				if(!ParserUtilities.isEmpty(givenNameElement.getAttribute("qualifier")))
				{
					participant.setPreviousName(ParserUtilities.readTextContext(givenNameElement));
				}else if (i == 0) {
					participant.setFirstName(ParserUtilities.readTextContext(givenNameElement));
				}else {
					participant.setMiddleName(ParserUtilities.readTextContext(givenNameElement));
				}
			}
			
			participant.setLastName(ParserUtilities.readTextContext((Element) CCDAConstants.REL_FAMILY_NAME_EXP.
					evaluate(nameElement, XPathConstants.NODE)));
			participant.setSuffix(ParserUtilities.readTextContext((Element) CCDAConstants.REL_SUFFIX_EXP.
					evaluate(nameElement, XPathConstants.NODE)));
		}
	}

}
