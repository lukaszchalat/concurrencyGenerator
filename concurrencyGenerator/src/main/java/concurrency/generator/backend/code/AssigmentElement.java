package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;

public class AssigmentElement extends CodeElement {
	
	private String variableName;
	
	private String variableValue;
	
	public AssigmentElement(String variablename, String variableValue) {
		super(ASSIGMENT_ELEMENT);
		this.variableName = variablename;
		this.variableValue = variableValue;
	}

	public String getVariableName() {
		return variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}
}
