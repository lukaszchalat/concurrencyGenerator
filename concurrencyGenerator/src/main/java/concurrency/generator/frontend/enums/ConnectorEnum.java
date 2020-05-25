package concurrency.generator.frontend.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConnectorEnum {
	RIGHT("Right", "/images/right.png"), 
	LEFT("Left", "/images/left.png"),
	TOP("Top", "/images/top.png"), 
	BOTTOM("Bottom", "/images/bottom.png"), 
	TOP_RIGHT("Top right", "/images/top_right.png"), 
	TOP_LEFT("Top left", "/images/top_left.png"), 
	BOTTOM_RIGHT("Bottom right", "/images/bottom_right.png"), 
	BOTTOM_LEFT("Bottom left", "/images/bottom_left.png");
	
	private String directory;
	private String path;
	
	private ConnectorEnum(String directory, String path) {
		this.directory = directory;
		this.path = path;
	}
	
	public String getPath( ) {
		return this.path;
	}
	
	public String getDirectory( ) {
		return this.directory;
	}
	
	public static ConnectorEnum getByDirectory(String directory) {
		return Arrays.asList(values()).stream().filter(res -> res.getDirectory().equals(directory)).findFirst().orElse(null);
	}
	
	public static List<String> getAllDirectories() {
		return Arrays.asList(values()).stream().map(res -> res.getDirectory()).collect(Collectors.toList());
	}
}
