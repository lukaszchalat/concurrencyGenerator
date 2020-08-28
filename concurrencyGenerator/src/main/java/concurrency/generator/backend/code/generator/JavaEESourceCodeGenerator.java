package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.enums.CodeElementEnum.ASSIGMENT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.FOR_LOOP_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.OUTPUT_ELEMENT;
import static concurrency.generator.backend.enums.CodeElementEnum.WHILE_LOOP_ELEMENT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.task.TaskExecutor;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.AssigmentElement;
import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.ForLoopElement;
import concurrency.generator.backend.code.OutputElement;
import concurrency.generator.backend.code.WhileLoopElement;

public class JavaEESourceCodeGenerator extends SourceCodeGenerator {
	
	private final ClassName resource = ClassName.get("javax.annotation", "Resource");
	private final ClassName servlet = ClassName.get("javax.servlet.http", "HttpServlet");
	private final String JAVA_EE_EXECUTOR_STRING = "managedExecutorService.execute(task);";
	
	public JavaEESourceCodeGenerator(String className) {
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
				String loopCode = generateLoopCode(element.getCodeElements(), JAVA_EE_EXECUTOR_STRING);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
			}
			else if(codeElement.getCodeElementType().equals(WHILE_LOOP_ELEMENT)) {
				WhileLoopElement element = (WhileLoopElement) codeElement;
				String loopCode = generateLoopCode(element.getCodeElements(), JAVA_EE_EXECUTOR_STRING);
				codeBlock.append(element.toString().replace("%loopCode%", loopCode));
			}
			else if(codeElement.getCodeElementType().equals(OUTPUT_ELEMENT)) {
				codeBlock.append(((OutputElement) codeElement).toString());
			}
		}
		
		FieldSpec managedExecutorServiceField = FieldSpec.builder(TaskExecutor.class, "taskExecutor")
				                                         .addModifiers(Modifier.PRIVATE)
				                                         .addAnnotation(AnnotationSpec.builder(resource).addMember("name", "DefaultManagedExecutorService").build())
				                                         .build();
		
		fields.add(managedExecutorServiceField);
		
		MethodSpec doGet = MethodSpec.methodBuilder("doGet")
                .addModifiers(Modifier.PROTECTED)
                .returns(void.class)
                .addParameter(HttpServletRequest.class, "request")
                .addParameter(HttpServletResponse.class, "response")
                .addException(ServletException.class)
                .addException(IOException.class)
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, doGet, servlet);
	}
}
