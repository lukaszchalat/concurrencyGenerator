package concurrency.generator.backend.code.generator;

import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import concurrency.generator.backend.code.CodeElement;

public abstract class SourceCodeGenerator {
	
	protected TypeSpec mainTemplateClass;
	
	protected String className;
	
	public SourceCodeGenerator(String className) {
		this.className = className;
	}
	
	protected TypeSpec createMainTemplateClass(List<FieldSpec> fields, MethodSpec mainMethod) {
	
		mainTemplateClass = TypeSpec.classBuilder(className)
				                    .addModifiers(Modifier.PUBLIC)
				                    .addMethod(mainMethod)
				                    .addFields(fields)
				                    .build();
		
		return mainTemplateClass;
	}
	
	public abstract TypeSpec generateSourceCode(List<CodeElement> codeElements);
}
