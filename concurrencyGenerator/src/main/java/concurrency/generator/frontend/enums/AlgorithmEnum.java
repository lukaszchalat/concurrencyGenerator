package concurrency.generator.frontend.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AlgorithmEnum {
	NONE(""),
	FIBONACCI("Fibonacci");
	
	private String name;
	
	private AlgorithmEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static List<String> getAllNames() {
		return Arrays.asList(values()).stream().map(algorithm -> algorithm.getName()).collect(Collectors.toList());
	}
	
	public static AlgorithmEnum getByName(String name) {
		return Arrays.asList(values()).stream().filter(algorithm -> algorithm.getName().equals(name)).findFirst().orElse(null);
	}
}
