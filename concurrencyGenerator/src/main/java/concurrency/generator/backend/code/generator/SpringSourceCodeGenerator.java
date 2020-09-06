	package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.code.generator.ClassNameProvider.LOCK;
import static concurrency.generator.backend.code.generator.ClassNameProvider.REENTRANTLOCK;

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
		super(className, "taskExecutor.execute(task);\n");
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = getAllFields(codeElements);
		String codeBlock = generateCodeBlock(codeElements);
		
		FieldSpec taskExecutorField = FieldSpec.builder(TaskExecutor.class, "taskExecutor")
				                               .addModifiers(Modifier.PRIVATE)
				                               .addAnnotation(autowired)
				                               .build();
		
		fields.add(taskExecutorField);
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T lock = new $T()", LOCK, REENTRANTLOCK)
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, mainMethod, null);
	}
}
