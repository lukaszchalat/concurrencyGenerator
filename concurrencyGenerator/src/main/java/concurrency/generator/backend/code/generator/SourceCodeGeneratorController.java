package concurrency.generator.backend.code.generator;

import java.io.IOException;
import java.util.List;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class SourceCodeGeneratorController {
	
	public static void generateSourceCode(List<CodeElement> codeElements, String javaTechnology, String algorithm) throws IOException {
		
		TypeSpec generatedSourceClass;
		String className = createClassName(javaTechnology, algorithm);
		
		if(javaTechnology.equals("Java SE")) {
			generatedSourceClass = new JavaSESourceCodeGenerator(className).generateSourceCode(codeElements);
		}
		else if(javaTechnology.equals("Java EE")) {
			generatedSourceClass = new JavaEESourceCodeGenerator(className).generateSourceCode(codeElements);
		}
		else if(javaTechnology.equals("SPRING")) {
			generatedSourceClass = new SpringSourceCodeGenerator(className).generateSourceCode(codeElements);
		}
		else {
			throw new UnsupportedOperationException("Unsupported Java Technology " + javaTechnology);
		}
		
		JavaFile javaFile = JavaFile.builder("concurrency.generator.backend.code.source", generatedSourceClass).build();
		
		javaFile.writeTo(System.out);
	}
	
	private static String createClassName(String javaTechnology, String algorithm) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(javaTechnology);
		sb.append(algorithm);
		sb.append("GeneratedClass");
		
		return sb.toString().replaceAll(" ", "");
	}
}
