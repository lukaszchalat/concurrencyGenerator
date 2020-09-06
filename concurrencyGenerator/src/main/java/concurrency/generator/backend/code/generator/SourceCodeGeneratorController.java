package concurrency.generator.backend.code.generator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public class SourceCodeGeneratorController {
	
	public static void generateSourceCode(List<CodeElement> codeElements, String javaTechnology, String algorithm, String targetDirectory) throws IOException {
		
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
		
		String packageName = targetDirectory.isEmpty() ? "concurrency.generator.backend.results" : "";
		targetDirectory = targetDirectory.isEmpty() ? "./src/main/java" : targetDirectory;
		
		JavaFile javaFile = JavaFile.builder(packageName, generatedSourceClass).build();
		
		javaFile.writeTo(Paths.get(targetDirectory));
	}
	
	private static String createClassName(String javaTechnology, String algorithm) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(javaTechnology);
		sb.append(algorithm);
		sb.append("GeneratedClass");
		
		return sb.toString().replaceAll(" ", "");
	}
}
