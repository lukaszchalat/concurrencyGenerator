package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.OPERATION_ELEMENT;

public class OperationElement extends CodeElement {
	
	private String assigmentVariable;
	
	private String firstVariable;
	
	private String secondVariable;
	
	private String operation;

	public OperationElement(String assigmentValue, String firstVariable, String secondVariable, String operation) {
		super(OPERATION_ELEMENT);
		this.assigmentVariable = assigmentValue;
		this.firstVariable = firstVariable;
		this.secondVariable = secondVariable;
		this.operation = operation;
	}

	public String getAssigmentVariable() {
		return assigmentVariable;
	}

	public void setAssigmentVariable(String assigmentVariable) {
		this.assigmentVariable = assigmentVariable;
	}

	public String getFirstVariable() {
		return firstVariable;
	}

	public void setFirstVariable(String firstVariable) {
		this.firstVariable = firstVariable;
	}

	public String getSecondVariable() {
		return secondVariable;
	}

	public void setSecondVariable(String secondVariable) {
		this.secondVariable = secondVariable;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String toString() {
		StringBuilder operationString = new StringBuilder();
		
		operationString.append(assigmentVariable +  " = ")
		         	.append(firstVariable + " ")
		            .append(operation + " ")
		            .append(secondVariable + ";\n");
		
		return operationString.toString();
	}
}
