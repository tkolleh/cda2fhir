package tr.com.srdc.cda2fhir.transform.section.impl;

import java.util.Map;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Procedure;
import org.openhealthtools.mdht.uml.cda.consol.ProcedureActivityProcedure;
import org.openhealthtools.mdht.uml.cda.consol.ProceduresSection;

import tr.com.srdc.cda2fhir.transform.IResourceTransformer;
import tr.com.srdc.cda2fhir.transform.section.ICDASection;
import tr.com.srdc.cda2fhir.transform.util.IBundleInfo;
import tr.com.srdc.cda2fhir.util.FHIRUtil;

public class CDAProceduresSection implements ICDASection {
	private ProceduresSection section;
	
	@SuppressWarnings("unused")
	private CDAProceduresSection() {};
	
	public CDAProceduresSection(ProceduresSection section) {
		this.section = section;
	}
	
	@Override
	public SectionResultSingular<Procedure> transform(IBundleInfo bundleInfo) {
		IResourceTransformer rt = bundleInfo.getResourceTransformer();
		Map<String, String> annotations = bundleInfo.getIdedAnnotations();
		Bundle result = new Bundle();
    	for(ProcedureActivityProcedure act : section.getConsolProcedureActivityProcedures()) {
    		Bundle bundle = rt.tProcedure2Procedure(act, annotations);
    		FHIRUtil.mergeBundle(bundle, result);
    	}
    	return SectionResultSingular.getInstance(result, Procedure.class);
	}
}
