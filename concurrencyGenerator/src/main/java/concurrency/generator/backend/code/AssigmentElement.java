package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;

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
	
	public String toString() {
		StringBuilder assigment = new StringBuilder();
		
		assigment.append(variableName + " = ")
		         .append(variableValue + ";\n");
		
		return assigment.toString();
	}
	
	public FieldSpec getAsField() {
		return FieldSpec.builder(Integer.class, variableName)
                        .addModifiers(Modifier.PRIVATE, Modifier.VOLATILE, Modifier.STATIC)
                        .build();
	}
}
