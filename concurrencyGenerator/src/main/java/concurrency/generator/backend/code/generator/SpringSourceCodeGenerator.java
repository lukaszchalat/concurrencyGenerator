package concurrency.generator.backend.code.generator;

import java.util.List;

import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class SpringSourceCodeGenerator extends SourceCodeGenerator {

	public SpringSourceCodeGenerator(String className) {
		super(className);
	}

	@Override
	public TypeSpec generateSourceCode(List<CodeElement> codeElements) {
		return null;
	}
}
