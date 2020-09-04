package concurrency.generator.backend.code;

import java.util.ArrayList;
import java.util.List;

import concurrency.generator.backend.enums.CodeElementEnum;

public class CodeElement {
	
	private CodeElementEnum codeElementType;
	
	private List<CodeElement> codeElements;
	
	public CodeElement(CodeElementEnum codeElementType) {
		this.codeElementType = codeElementType;
		this.codeElements = new ArrayList<>();
	}

	public CodeElementEnum getCodeElementType() {
		return codeElementType;
	}

	public List<CodeElement> getCodeElements() {
		return codeElements;
	}
	
	public void setCodeElements(List<CodeElement> codeElements) {
		this.codeElements = codeElements;
	}

	public void addCodeElement(CodeElement codeElement) {
		this.codeElements.add(codeElement);
	}

}
