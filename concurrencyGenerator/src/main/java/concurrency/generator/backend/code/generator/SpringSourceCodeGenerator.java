package concurrency.generator.backend.code.generator;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.core.task.TaskExecutor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class SpringSourceCodeGenerator extends SourceCodeGenerator {
	
	private final ClassName autowired = ClassName.get("org.springframework.beans.factory.annotation", "Autowired");

	public SpringSourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = new ArrayList<>();
		StringBuilder codeBlock = new StringBuilder();
		
		generateFieldsAndCodeBlock(codeElements, fields, codeBlock, SPRING_EXECUTOR_STRING);
		
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
		
		return createMainTemplateClass(fields, mainMethod, null);
	}
}
