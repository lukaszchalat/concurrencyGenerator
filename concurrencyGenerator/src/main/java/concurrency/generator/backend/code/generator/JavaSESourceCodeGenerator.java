package concurrency.generator.backend.code.generator;

import static concurrency.generator.backend.code.generator.ClassNameProvider.*;

import java.util.List;
import javax.lang.model.element.Modifier;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import concurrency.generator.backend.code.CodeElement;

public class JavaSESourceCodeGenerator extends SourceCodeGenerator {
	
	public JavaSESourceCodeGenerator(String className) {
		super(className, "executorService.execute(task);\n");
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		
		List<FieldSpec> fields = getAllFields(codeElements);
		String codeBlock = generateCodeBlock(codeElements);
		
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T executorService = $T.newFixedThreadPool(5)", EXECUTOR_SERVICE, EXECUTORS)
                .addStatement("$T lock = new $T()", LOCK, REENTRANTLOCK)
                .addCode(codeBlock)
                .build();
		
		return createMainTemplateClass(fields, mainMethod, null);
	}
}
