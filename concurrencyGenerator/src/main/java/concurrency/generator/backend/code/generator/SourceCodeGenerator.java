package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.FOR_LOOP_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.OPERATION_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.OUTPUT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.WHILE_LOOP_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.IF_ELSE_ELEMENT;

import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.AssigmentElement;
import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.ForLoopElement;
import concurrency.generator.backend.code.IfElseElement;
import concurrency.generator.backend.code.OperationElement;
import concurrency.generator.backend.code.OutputElement;
import concurrency.generator.backend.code.WhileLoopElement;

public abstract class SourceCodeGenerator {
	
	protected final String JAVA_SE_EXECUTOR_STRING = "executorService.execute(task);\n";
	protected final String JAVA_EE_EXECUTOR_STRING = "managedExecutorService.execute(task);\n";
	protected final String SPRING_EXECUTOR_STRING = "taskExecutor.execute(task);\n";
	protected String className;
	protected final ClassName lock = ClassName.get("java.util.concurrent.locks", "Lock");
	protected final ClassName reentrantLock = ClassName.get("java.util.concurrent.locks", "ReentrantLock");
	
	public SourceCodeGenerator(String className) {
		this.className = className;
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
	
	protected String generateLoopCode(List<CodeElement> codeElements, String executor) {
		StringBuilder loopCode = new StringBuilder();
		
		loopCode.append("\tRunnable task = () -> {\n");
		if(executor.equals(JAVA_SE_EXECUTOR_STRING)) loopCode.append("\tlock.lock();\n");
		
		for(CodeElement codeElement: codeElements) {
			if(codeElement.getCodeElementType().equals(OPERATION_ELEMENT)) {
				loopCode.append("\t\t").append(((OperationElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(ASSIGMENT_ELEMENT)) {
				loopCode.append("\t\t").append(((AssigmentElement) codeElement).toString());
			}
		}
		
		if(executor.equals(JAVA_SE_EXECUTOR_STRING)) loopCode.append("\tlock.unlock();\n");
		
		loopCode.append("  };\n");
		loopCode.append(executor);
		
		return loopCode.toString();
	}
	
	protected void generateFieldsAndCodeBlock(List<CodeElement> codeElements, List<FieldSpec> fields, StringBuilder codeBlock, String executor) {
		
		for(CodeElement codeElement: codeElements) {
			if(codeElement.getCodeElementType().equals(ASSIGMENT_ELEMENT) && !((AssigmentElement) codeElement).getVariableName().equals("i")) {
				AssigmentElement element = (AssigmentElement) codeElement;
				FieldSpec field = FieldSpec.builder(Integer.class, element.getVariableName())
						                   .addModifiers(Modifier.PRIVATE, Modifier.VOLATILE, Modifier.STATIC)
						                   .build();
				
				fields.add(field);
				codeBlock.append(((AssigmentElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(FOR_LOOP_ELEMENT)) {
				ForLoopElement element = (ForLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements(), executor);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
				if(executor.equals(JAVA_SE_EXECUTOR_STRING)) codeBlock.append("executorService.shutdown(); \n");
			}
			else if(codeElement.getCodeElementType().equals(WHILE_LOOP_ELEMENT)) {
				WhileLoopElement element = (WhileLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements(), executor);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
				if(executor.equals(JAVA_SE_EXECUTOR_STRING)) codeBlock.append("executorService.shutdown(); \n");
			}
			else if(codeElement.getCodeElementType().equals(OUTPUT_ELEMENT)) {
				codeBlock.append(((OutputElement) codeElement).toString());
			}
			else if(codeElement.getCodeElementType().equals(IF_ELSE_ELEMENT)) {
				codeBlock.append(((IfElseElement) codeElement).toString());
			}
		}	
	}
}
