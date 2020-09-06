package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.code.generator.ClassNameProvider.LOCK;
import static concurrency.generator.backend.code.generator.ClassNameProvider.REENTRANTLOCK;

import java.io.IOException;
import java.util.List;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.lang.model.element.Modifier;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		super(className, "managedExecutorService.execute(task);\n");
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = getAllFields(codeElements);
		String codeBlock = generateCodeBlock(codeElements);
		
		FieldSpec managedExecutorServiceField = FieldSpec.builder(ManagedExecutorService.class, "managedExecutorService")
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
                .addStatement("$T lock = new $T()", LOCK, REENTRANTLOCK)
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, doGet, servlet);
	}
}
