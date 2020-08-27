package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.OUTPUT_ELEMENT;

public class OutputElement extends CodeElement {
	
	private String output;
	
	public OutputElement(String output) {
		super(OUTPUT_ELEMENT);
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public String toString() {
		return "\nSystem.out.println(" + output + ");\n";
	}
}
