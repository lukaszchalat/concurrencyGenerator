package concurrency.generator.backend.converter;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;
import static concurrency.generator.backend.enums.CodeElementEnum.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import concurrency.generator.backend.code.*;
import concurrency.generator.frontend.flowchart.AssigmentFlowchart;
import concurrency.generator.frontend.flowchart.DecisionFlowchart;
import concurrency.generator.frontend.flowchart.Flowchart;
import concurrency.generator.frontend.flowchart.OperationFlowchart;
import concurrency.generator.frontend.flowchart.OutputFlowchart;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class CodeConverter {
	
	private List<CodeElement> codeElements;
	private List<Flowchart> flowcharts;
	private FlowchartFinder flowchartFinder;
	
	public CodeConverter(List<Flowchart> flowcharts) {
		this.flowcharts = flowcharts.stream().filter(flowchart -> flowchart.isNotEmpty()).collect(Collectors.toList());
		this.flowchartFinder = new FlowchartFinder(this.flowcharts);
		this.codeElements = new ArrayList<>();
	}
	
	public List<CodeElement> convertToCodeElements() {
		
		Flowchart currentFlowchart = flowchartFinder.findStartFlowchart();
		
		while(!currentFlowchart.getType().equals(STOP)) {
			System.out.println(currentFlowchart.getType());
			if(!currentFlowchart.isConnector()) {
				convert(currentFlowchart, codeElements);
			}	
			
			currentFlowchart = currentFlowchart.getType().equals(DECISION) && checkIfLoop(currentFlowchart) ? flowchartFinder.goLeft(currentFlowchart) 
					                                                                                        : flowchartFinder.findNextFlowchart(currentFlowchart);
		}
	
		return codeElements;
	}
	
	private void convert(Flowchart flowchart, List<CodeElement> codeElements) {
		
		if(flowchart.getType().equals(ASSIGMENT)) {
			codeElements.addAll(convertToAssigmentElements(flowchart));
		}
		else if(flowchart.getType().equals(DECISION)) {
			if(checkIfLoop(flowchart)) {
				codeElements.add(convertToLoop(getLoopFlowcharts(flowchart)));
			}
		}
		else if(flowchart.getType().equals(OPERATION)) {
			codeElements.add(convertToOperationElement(flowchart));
		}
		else if(flowchart.getType().equals(OUTPUT)) {
			codeElements.add(convertToOutputElement(flowchart));
		}
		
	}
	
	private List<CodeElement> convertToAssigmentElements(Flowchart flowchart) {
		List<CodeElement> elements = new ArrayList<>();

		for(Map.Entry<JTextField, JTextField> entry: ((AssigmentFlowchart) flowchart).getAssigmentMapping().entrySet()) {
			String name = entry.getKey().getText();
			String value = entry.getValue().getText();
			if(name != null && !name.isEmpty() && value != null && !value.isEmpty()) {
				elements.add(new AssigmentElement(name, value));
			}
		}
		
		return elements;
	}
	
	private CodeElement convertToLoop(List<Flowchart> loopFlowcharts) {
		ForLoopElement forLoop = new ForLoopElement();
		
		DecisionFlowchart decisionFlowchart = (DecisionFlowchart) loopFlowcharts.stream().filter(flowchart -> flowchart.getType().equals(DECISION)).findAny().get();
		
		String iteratorName = decisionFlowchart.getLeftVariable().getText();
		
		String iteratorStartValue = codeElements.stream().filter(element -> element.getCodeElementType().equals(ASSIGMENT_ELEMENT))
				                                         .filter(element -> ((AssigmentElement) element).getVariableName().equals(iteratorName))
				                                         .map(element -> ((AssigmentElement) element).getVariableValue())
				                                         .findAny().get();
		
		String conditionSign = decisionFlowchart.getOptions().getSelectedItem().toString();
		String conditionValue = decisionFlowchart.getRightVariable().getText();
		
		OperationFlowchart iteratorOperationFlowchart = (OperationFlowchart) loopFlowcharts.stream().filter(flowchart -> flowchart.getType().equals(OPERATION))
				                                                                                    .filter(flowchart -> ((OperationFlowchart) flowchart).getAssigmentVariable().getText().equals(iteratorName))
				                                                                                    .findAny().get();
		
		String iteratorChangeSign = iteratorOperationFlowchart.getOperations().getSelectedItem().toString();
		String iteratorChangeValue = iteratorOperationFlowchart.getSecondVariable().getText();
		
		forLoop.setIteratorName(iteratorName);
		forLoop.setIteratorStartValue(iteratorStartValue);
		forLoop.setConditionSign(conditionSign);
		forLoop.setConditionValue(conditionValue);
		forLoop.setIteratorChangeSign(iteratorChangeSign);
		forLoop.setIteratorChangeValue(iteratorChangeValue);
		
		loopFlowcharts.remove(decisionFlowchart);
		loopFlowcharts.remove(iteratorOperationFlowchart);
		
		for(Flowchart flowchart: loopFlowcharts) {
			convert(flowchart, forLoop.getCodeElements());
		}
		
		return forLoop;
	}
	
	private CodeElement convertToOperationElement(Flowchart flowchart) {
		OperationFlowchart operationFlowchart = (OperationFlowchart) flowchart;
		
		String assigmentValue = operationFlowchart.getAssigmentVariable().getText();
		String firstVariable = operationFlowchart.getFirstVariable().getText();
		String secondVariable = operationFlowchart.getSecondVariable().getText();
		String operation = operationFlowchart.getOperations().getSelectedItem().toString();
		
		return new OperationElement(assigmentValue, firstVariable, secondVariable, operation);
	}
	
	private CodeElement convertToOutputElement(Flowchart flowchart) {
		String output = ((OutputFlowchart) flowchart).getOutputVariable().getText();
		
		return new OutputElement(output);
	}
	
	private boolean checkIfLoop(Flowchart flowchart) {
		MatrixCoordinates coordinates = flowchart.getCoordinates();
		int column = coordinates.getColumn();
		int row = coordinates.getRow();
		Flowchart nextOnRightFlowchart = flowchartFinder.check("RIGHT", row, column + 1).get();
		
		while(true) {
			
			if(!nextOnRightFlowchart.isConnector()) {
				if(nextOnRightFlowchart.getType().equals(STOP)) return false;
				else if(nextOnRightFlowchart.equals(flowchart)) return true;
			}
			
			nextOnRightFlowchart = flowchartFinder.findNextFlowchart(nextOnRightFlowchart);
		}
	}
	
	private List<Flowchart> getLoopFlowcharts(Flowchart flowchart) {
		List<Flowchart> loopFlowcharts = new ArrayList<>();
		MatrixCoordinates coordinates = flowchart.getCoordinates();
		int column = coordinates.getColumn();
		int row = coordinates.getRow();
		Flowchart nextLoopFlowchart = flowchartFinder.check("RIGHT", row, column + 1).get();
		
		loopFlowcharts.add(flowchart);
		
		while(!nextLoopFlowchart.equals(flowchart)) {
			loopFlowcharts.add(nextLoopFlowchart);
			nextLoopFlowchart = flowchartFinder.findNextFlowchart(nextLoopFlowchart);
		}
		
		return loopFlowcharts;
	}
}
