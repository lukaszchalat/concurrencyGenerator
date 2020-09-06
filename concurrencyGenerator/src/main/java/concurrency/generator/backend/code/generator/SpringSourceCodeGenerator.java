	package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.code.generator.ClassNameProvider.LOCK;
import static concurrency.generator.backend.code.generator.ClassNameProvider.REENTRANTLOCK;
import static concurrency.generator.backend.code.generator.ClassNameProvider.TASK_EXECUTOR;

import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class SpringSourceCodeGenerator extends SourceCodeGenerator {

	public SpringSourceCodeGenerator(String className) {
		super(className, "taskExecutor.execute(task);\n");
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = getAllFields(codeElements);
		String codeBlock = generateCodeBlock(codeElements);
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T lock = new $T()", LOCK, REENTRANTLOCK)
                .addStatement("$T taskExecutor = new $T()", TASK_EXECUTOR, TASK_EXECUTOR)
                .addStatement("taskExecutor.setCorePoolSize(4);")
                .addStatement("taskExecutor.setMaxPoolSize(4);")
                .addStatement("taskExecutor.initialize();")
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, mainMethod, null);
	}
}
