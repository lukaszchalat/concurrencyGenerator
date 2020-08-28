package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.FOR_LOOP_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.OUTPUT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.WHILE_LOOP_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.core.task.TaskExecutor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.AssigmentElement;
import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.ForLoopElement;
import concurrency.generator.backend.code.OutputElement;
import concurrency.generator.backend.code.WhileLoopElement;

public class SpringSourceCodeGenerator extends SourceCodeGenerator {
	
	private final ClassName autowired = ClassName.get("org.springframework.beans.factory.annotation", "Autowired");
	private final String SPRING_EXECUTOR_STRING = "taskExecutor.execute(task);";

	public SpringSourceCodeGenerator(String className) {
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
				String loopCode = generateLoopCode(element.getCodeElements(), SPRING_EXECUTOR_STRING);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
			}
			else if(codeElement.getCodeElementType().equals(WHILE_LOOP_ELEMENT)) {
				WhileLoopElement element = (WhileLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements(), SPRING_EXECUTOR_STRING);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
			}
			else if(codeElement.getCodeElementType().equals(OUTPUT_ELEMENT)) {
				codeBlock.append(((OutputElement) codeElement).toString());
			}
		}
		
		FieldSpec taskExecutorField = FieldSpec.builder(TaskExecutor.class, "taskExecutor")
				                               .addModifiers(Modifier.PRIVATE)
				                               .addAnnotation(autowired)
				                               .build();
		
		fields.add(taskExecutorField);
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, mainMethod);
	}
}
