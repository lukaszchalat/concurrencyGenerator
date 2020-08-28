package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.OPERATION_ELEMENT;

import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.AssigmentElement;
import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.OperationElement;

public abstract class SourceCodeGenerator {
	
	protected TypeSpec mainTemplateClass;
	
	protected String className;
	
	public SourceCodeGenerator(String className) {
		this.className = className;
	}
	
	protected TypeSpec createMainTemplateClass(List<FieldSpec> fields, MethodSpec mainMethod) {
	
		mainTemplateClass = TypeSpec.classBuilder(className)
				                    .addModifiers(Modifier.PUBLIC)
				                    .addMethod(mainMethod)
				                    .addFields(fields)
				                    .build();
		
		return mainTemplateClass;
	}
	
	public abstract TypeSpec generateSourceCode(List<CodeElement> codeElements);
	
	protected String generateLoopCode(List<CodeElement> codeElements, String executor) {
		StringBuilder loopCode = new StringBuilder();
		
		loopCode.append("\tRunnable task = () -> {\n");
		
		for(CodeElement codeElement: codeElements) {
			if(codeElement.getCodeElementType().equals(OPERATION_ELEMENT)) {
				loopCode.append("\t\t").append(((OperationElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(ASSIGMENT_ELEMENT)) {
				loopCode.append("\t\t").append(((AssigmentElement) codeElement).toString());
			}
		}
		
		loopCode.append("  };\n");
		loopCode.append("executorService.execute(task);");
		
		return loopCode.toString();
	}
}
