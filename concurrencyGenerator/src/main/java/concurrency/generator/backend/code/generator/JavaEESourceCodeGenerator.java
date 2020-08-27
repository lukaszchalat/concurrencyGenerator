package concurrency.generator.backend.code.generator;

import java.util.List;

import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class JavaEESourceCodeGenerator extends SourceCodeGenerator {
	
	public JavaEESourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		return null;
	}
}
