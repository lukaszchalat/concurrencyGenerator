package concurrency.generator.frontend.flowchart.util;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;

import concurrency.generator.frontend.flowchart.Flowchart;

public class TransportHelper {
	
	private static Flowchart target;
	
	private static Flowchart destination;

	public static Flowchart getTarget() {
		return target;
	}

	public static void setTarget(Flowchart target) {
		target.setBorder(BORDER_RED);
		TransportHelper.target = target;
	}

	public static Flowchart getDestination() {
		return destination;
	}

	public static void setDestination(Flowchart destination) {
		TransportHelper.destination = destination;
	}
	
	public static void clear() {
		if(target != null) {
			target.setBorder(BORDER_BLACK);
		}
		target = null;
		destination = null;
	}

}
