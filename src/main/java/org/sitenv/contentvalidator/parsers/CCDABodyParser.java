package org.sitenv.contentvalidator.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sitenv.contentvalidator.model.CCDARefModel;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathExpressionException;

public class CCDABodyParser {
	
	private static Logger log = LoggerFactory.getLogger(CCDABodyParser.class.getName());
	
	static public void parseBody(Document doc, CCDARefModel model, boolean curesUpdate, boolean svap2022)
			throws XPathExpressionException {
	
		log.info(" Parsing Encounters ");
		EncounterParser.parse(doc,model, curesUpdate, svap2022);
		
		log.info(" Parsing Problems ");
		ProblemParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info(" Parsing Medications ");
		MedicationParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Allergies ");
		AllergiesParser.parse(doc,model, curesUpdate, svap2022);
		
		log.info("Parsing Social History ");
		SocialHistoryParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info(" Parsing lab Results ");
		LabResultParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info(" Parsing lab tests ");
		LabTestParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Vitals ");
		VitalSignParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Procedures "); 
		ProcedureParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Care Team Members ");
		CareTeamMemberParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing CarePlan Sections ");
		CarePlanSectionsParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Immunizations ");
		ImmunizationParser.parse(doc, model, curesUpdate, svap2022);
		
		log.info("Parsing Medical Equipments");
		MedicalEquipmentParser.parse(doc, model, curesUpdate, svap2022);
		
		logUscdiTypesStatus(curesUpdate, svap2022);
		
		if (curesUpdate && svap2022) {
			// Note: The UI should not allow this. It should only be able to happen via misuse of the API.
			// TODO: consider throwing exception
			log.error("We can't process curesUpdate and svap2022 at the same time. Defaulting to curesUpdate.");
			svap2022 = false;
		}
		
		if (curesUpdate) {
			log.info(" Parsing Notes Section ");
			NotesParser.parse(doc, model, curesUpdate, svap2022);

			// Not required by the spec but required by our scenarios due to them having authors in the header
			log.info(" Parsing Doc Author ");
			AuthorParser.parse(doc, model, curesUpdate, svap2022);

			log.info(" Parsing Care Team Section ");
			CareTeamMemberParser.parseCareTeamSection(doc, model, curesUpdate, svap2022);
		} /*else if (svap2022) { // TODO: Add this condition back when we have specifics - NOTE: cures is FORCED by CCDAParser.java for now, so we don't miss out on if (curesUPdate) checks for now...
			// TODO: Add svap2022 (USCDI V2) specific parsing requirements
		}*/
		
	}
	
	private static void logUscdiTypesStatus(boolean curesUpdate, boolean svap2022) {
		log.info("logUscdiTypesStatus()");
		log.info("curesUpdate: " + curesUpdate);
		log.info("svap2022: " + svap2022);
	}
}