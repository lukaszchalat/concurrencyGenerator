package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import com.squareup.javapoet.*;
import concurrency.generator.backend.code.*;

public abstract class SourceCodeGenerator {
	
	protected String className;
	protected String executor;
	
	
	public SourceCodeGenerator(String className, String executor) {
		this.className = className;
		this.executor = executor;
	}
	
	protected TypeSpec createMainTemplateClass(List<FieldSpec> fields, MethodSpec mainMethod, TypeName superClass) {
	
		TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
				                                .addModifiers(Modifier.PUBLIC)
				                                .addMethod(mainMethod)
				                                .addFields(fields);
		
		if(superClass != null) {
			classBuilder.superclass(superClass);
		}
		
		return classBuilder.build();
	}
	
	public abstract TypeSpec generateSourceCode(List<CodeElement> codeElements);
	
	protected String generateLoopCode(List<CodeElement> codeElements) {
		StringBuilder loopCode = new StringBuilder();
		
		loopCode.append("\tRunnable task = () -> {\n");
		loopCode.append("\tlock.lock();\n");
		
		loopCode.append(generateCodeBlock(codeElements));
		
		loopCode.append("\tlock.unlock();\n");
		loopCode.append("  };\n");
		loopCode.append(executor);
		
		return loopCode.toString();
	}
	
	protected String generateIfElseCodeBlock(IfElseElement ifElseElement) {
		String passedCodeBlock = generateCodeBlock(ifElseElement.getPassedConditionElements());
		String failedCodeBlock = generateCodeBlock(ifElseElement.getFailedConditionElements());
		
		return ifElseElement.toString().replace("%passedCode%", passedCodeBlock).replace("%failedCode%", failedCodeBlock);
	}
	
	protected String generateCodeBlock(List<CodeElement> codeElements) {
		
		StringBuilder codeBlock = new StringBuilder();
		
		for(CodeElement codeElement: codeElements) {
			if(codeElement.getCodeElementType().equals(ASSIGMENT_ELEMENT) && !((AssigmentElement) codeElement).getVariableName().equals("i")) {
				codeBlock.append(((AssigmentElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(FOR_LOOP_ELEMENT)) {
				ForLoopElement element = (ForLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements());
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
				if(executor.equals("executorService.execute(task);\n")) codeBlock.append("executorService.shutdown(); \n");
				if(executor.equals("taskExecutor.execute(task);\n")) codeBlock.append("taskExecutor.shutdown(); \n");
			}
			else if(codeElement.getCodeElementType().equals(WHILE_LOOP_ELEMENT)) {
				WhileLoopElement element = (WhileLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements());
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
				if(executor.equals("executorService.execute(task);\n")) codeBlock.append("executorService.shutdown(); \n");
				if(executor.equals("taskExecutor.execute(task);\n")) codeBlock.append("taskExecutor.shutdown(); \n");
			}
			else if(codeElement.getCodeElementType().equals(OUTPUT_ELEMENT)) {
				codeBlock.append(((OutputElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(IF_ELSE_ELEMENT)) {
				codeBlock.append(generateIfElseCodeBlock((IfElseElement) codeElement));
			}
			else if(codeElement.getCodeElementType().equals(OPERATION_ELEMENT) && !((OperationElement) codeElement).getAssigmentVariable().equals("i")) {
				codeBlock.append(((OperationElement) codeElement).toString());
			}
		}	
		
		return codeBlock.toString();
	}
	
	protected List<FieldSpec> getAllFields(List<CodeElement> elements) {
		return elements.stream().filter(element -> element.getCodeElementType().equals(ASSIGMENT_ELEMENT) && !((AssigmentElement) element).getVariableName().equals("i"))
				                .map(element -> ((AssigmentElement) element).getAsField())
				                .collect(Collectors.toList());
	}
}
