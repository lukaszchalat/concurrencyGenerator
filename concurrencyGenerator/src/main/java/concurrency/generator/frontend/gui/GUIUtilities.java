package concurrency.generator.frontend.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import concurrency.generator.frontend.flowchart.Flowchart;

public class GUIUtilities {
	
	public static List<Flowchart> convertToList(Flowchart flowchartMatrix[][]) {
		List<Flowchart> list = new ArrayList<Flowchart>();
		
		for(Flowchart[] flowcharts : flowchartMatrix) {
			list.addAll(Arrays.asList(flowcharts));
		}
		
		return list;
	}

}
