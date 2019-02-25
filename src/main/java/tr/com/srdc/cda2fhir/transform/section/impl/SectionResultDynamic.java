package tr.com.srdc.cda2fhir.transform.section.impl;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Resource;

import tr.com.srdc.cda2fhir.transform.section.ISectionResult;
import tr.com.srdc.cda2fhir.util.FHIRUtil;

public class SectionResultDynamic implements ISectionResult  {
	private Bundle bundle = new Bundle();
	private List<Resource> resources = new ArrayList<Resource>();
	

	public void add(Resource resource) {
        bundle.addEntry().setResource(resource);
		resources.add(resource);
	}
	
	public <T extends Resource> void merge(Bundle bundle, Class<T> clazz) {
   		FHIRUtil.mergeBundle(bundle, this.bundle);
   		List<? extends Resource> sectionResources = FHIRUtil.findResources(bundle, clazz);
		resources.addAll(sectionResources);
	}
	
	@Override
	public Bundle getBundle() {
		return bundle;
	}

	@Override
	public List<Resource> getSectionResources() {
		return resources;
	}
}