package tr.com.srdc.cda2fhir.jolt.report;

public interface IParentNode extends INode {
	IParentNode getParent();

	void removeChild(INode node);
}
