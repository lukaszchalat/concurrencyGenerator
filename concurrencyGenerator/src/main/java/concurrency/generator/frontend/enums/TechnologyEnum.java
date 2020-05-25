package concurrency.generator.frontend.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TechnologyEnum {
	JAVA_SE("Java SE"),
	JAVA_EE("Java EE"),
	SPRING("SPRING");
	
	private String name;
	
	private TechnologyEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static List<String> getAllNames() {
		return Arrays.asList(values()).stream().map(technology -> technology.getName()).collect(Collectors.toList());
	}
	
	public static TechnologyEnum getByName(String name) {
		return Arrays.asList(values()).stream().filter(technology -> technology.getName().equals(name)).findFirst().orElse(null);
	}
}
