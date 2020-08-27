package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.*;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.AssigmentElement;
import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.ForLoopElement;
import concurrency.generator.backend.code.OperationElement;
import concurrency.generator.backend.code.OutputElement;

public class JavaSESourceCodeGenerator extends SourceCodeGenerator {
	
	ClassName executorService = ClassName.get("java.util.concurrent", "ExecutorService");
	ClassName executors = ClassName.get("java.util.concurrent", "Executors");
	
	public JavaSESourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = new ArrayList<>();
		StringBuilder codeBlock = new StringBuilder();
		
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
				String loopCode = generateLoopCode(element.getCodeElements());
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
			}
			else if(codeElement.getCodeElementType().equals(OUTPUT_ELEMENT)) {
				codeBlock.append(((OutputElement) codeElement).toString());
			}
		}
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T executorService = $T.newFixedThreadPool(5)", executorService, executors)
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, mainMethod);
	}
	
	private String generateLoopCode(List<CodeElement> codeElements) {
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
