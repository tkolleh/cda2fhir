package tr.com.srdc.cda2fhir.jolt.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.com.srdc.cda2fhir.jolt.report.INode;
import tr.com.srdc.cda2fhir.jolt.report.JoltPath;
import tr.com.srdc.cda2fhir.jolt.report.Table;

public class RootNode implements INode {
	private JoltPath root = new JoltPath("root");

	@Override
	public void addChild(JoltPath node) {
		root.addChild(node);
	}
	
	@Override
	public List<JoltPath> getLinks() {
		return root.getLinks();
	}

	@Override
	public void expandLinks(Map<String, RootNode> linkMap) {
		root.expandLinks(linkMap);
	}

	@Override
	public void conditionalize() {
		root.createConditions();
		root.mergeSpecialDescendants();
	}

	@Override
	public Table toTable() {
		return root.toTable();
	}

	private List<JoltPath> getMergableCopy(String name) {
		JoltPath rootClone = root.clone();
		if (name.length() > 0) {
			rootClone.promoteTargets(name);
		}
		return rootClone.children;
	}

	public List<JoltPath> getAsLinkReplacement(String path, String target) {
		List<JoltPath> list = getMergableCopy(target);
		JoltPath node = new JoltPath(path);
		node.children.addAll(list);
		List<JoltPath> result = new ArrayList<JoltPath>();
		result.add(node);
		return result;
	}
}
