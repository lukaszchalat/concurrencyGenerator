package concurrency.generator.backend.code.generator;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class JavaSESourceCodeGenerator extends SourceCodeGenerator {
	
	private final ClassName executorService = ClassName.get("java.util.concurrent", "ExecutorService");
	private final ClassName executors = ClassName.get("java.util.concurrent", "Executors");
	
	public JavaSESourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = new ArrayList<>();
		StringBuilder codeBlock = new StringBuilder();
		
		generateFieldsAndCodeBlock(codeElements, fields, codeBlock, JAVA_SE_EXECUTOR_STRING);
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T executorService = $T.newFixedThreadPool(5)", executorService, executors)
                .addStatement("$T lock = new $T()", lock, reentrantLock)
                .addCode(codeBlock.toString())
                .build();
		
		return createMainTemplateClass(fields, mainMethod, null);
	}
}
