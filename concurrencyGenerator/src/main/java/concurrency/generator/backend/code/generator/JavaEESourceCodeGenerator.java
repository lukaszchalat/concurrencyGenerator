package concurrency.generator.backend.code.generator;

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

import concurrency.generator.backend.code.CodeElement;

public class JavaEESourceCodeGenerator extends SourceCodeGenerator {
	
	private final ClassName resource = ClassName.get("javax.annotation", "Resource");
	private final ClassName servlet = ClassName.get("javax.servlet.http", "HttpServlet");
	
	public JavaEESourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = new ArrayList<>();
		StringBuilder codeBlock = new StringBuilder();
		
		generateFieldsAndCodeBlock(codeElements, fields, codeBlock, JAVA_EE_EXECUTOR_STRING);
		
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
