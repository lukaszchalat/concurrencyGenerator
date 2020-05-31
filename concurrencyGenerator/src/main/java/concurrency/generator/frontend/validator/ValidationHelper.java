package concurrency.generator.frontend.validator;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.util.List;
import java.util.Optional;

import concurrency.generator.frontend.flowchart.Flowchart;

public class ValidationHelper {
	
	private List<Flowchart> flowcharts;
	
	public ValidationHelper(List<Flowchart> flowcharts) {
		this.flowcharts = flowcharts;
	}
	
	public boolean hasStartElement() {
		Optional<Flowchart> startFlowchart = flowcharts.stream().filter(flowchart -> flowchart.getType().equals(START)).findFirst();
		
		return startFlowchart.isPresent();
	}
	
	public boolean hasStopElement() {
		Optional<Flowchart> startFlowchart = flowcharts.stream().filter(flowchart -> flowchart.getType().equals(STOP)).findFirst();
		
		return startFlowchart.isPresent();
	}
}
