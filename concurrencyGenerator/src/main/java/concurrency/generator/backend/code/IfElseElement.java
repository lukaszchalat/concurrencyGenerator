package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.IF_ELSE_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import concurrency.generator.frontend.flowcharts.DecisionFlowchart;

public class IfElseElement extends CodeElement {
	
	private String leftConditionValue;
	private String rightConditionValue;
	private String conditionSign;
	private List<CodeElement> passedConditionElements = new ArrayList<>();
	private List<CodeElement> failedConditionElements = new ArrayList<>();

	public IfElseElement(DecisionFlowchart flowchart) {
		super(IF_ELSE_ELEMENT);
		this.leftConditionValue = flowchart.getLeftVariable().getText();
		this.rightConditionValue = flowchart.getRightVariable().getText();
		this.conditionSign = flowchart.getOptions().getSelectedItem().toString();
	}

	public String getLeftConditionValue() {
		return leftConditionValue;
	}

	public void setLeftConditionValue(String leftConditionValue) {
		this.leftConditionValue = leftConditionValue;
	}

	public String getRightConditionValue() {
		return rightConditionValue;
	}

	public void setRightConditionValue(String rightConditionValue) {
		this.rightConditionValue = rightConditionValue;
	}

	public String getConditionSign() {
		return conditionSign;
	}

	public void setConditionSign(String conditionSign) {
		this.conditionSign = conditionSign;
	}

	public List<CodeElement> getPassedConditionElements() {
		return passedConditionElements;
	}

	public void setPassedConditionElements(List<CodeElement> passedConditionElements) {
		this.passedConditionElements = passedConditionElements;
	}

	public List<CodeElement> getFailedConditionElements() {
		return failedConditionElements;
	}

	public void setFailedConditionElements(List<CodeElement> failedConditionElements) {
		this.failedConditionElements = failedConditionElements;
	}
	
	public String toString() {
		StringBuilder ifElse = new StringBuilder();
		
		ifElse.append("if(");
		ifElse.append(leftConditionValue + " ");
		ifElse.append(conditionSign + " ");
		ifElse.append(rightConditionValue + ") { \n");
		ifElse.append("\t %passedCode% \n");
		ifElse.append("} else { \n");
		ifElse.append("\t %failedCode% \n");
		ifElse.append("} \n");
		
		
		return ifElse.toString();
	}
}
