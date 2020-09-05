package concurrency.generator.backend.converter;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;
import static concurrency.generator.backend.enums.CodeElementEnum.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import concurrency.generator.backend.code.*;
import concurrency.generator.frontend.flowcharts.AssigmentFlowchart;
import concurrency.generator.frontend.flowcharts.DecisionFlowchart;
import concurrency.generator.frontend.flowcharts.Flowchart;
import concurrency.generator.frontend.flowcharts.OperationFlowchart;
import concurrency.generator.frontend.flowcharts.OutputFlowchart;

public class CodeConverter {
	
	private List<Flowchart> flowcharts;
	private FlowchartFinder flowchartFinder;
	private Flowchart loopStartFlowchart;
	private List<CodeElement> cachedAssigmentElements;
	private boolean isLoop;
	
	public CodeConverter(List<Flowchart> flowcharts) {
		this.flowcharts = flowcharts.stream().filter(flowchart -> flowchart.isNotEmpty()).collect(Collectors.toList());
		this.flowchartFinder = new FlowchartFinder(this.flowcharts);
		this.cachedAssigmentElements = new ArrayList<>();
	}
	
	public List<CodeElement> convertToCodeElements() {
		Flowchart currentFlowchart = flowchartFinder.findStartFlowchart();
		
		return convertToCodeElements(currentFlowchart);
	}
	
	private List<CodeElement> convertToCodeElements(Flowchart currentFlowchart) {
		
		List<CodeElement> codeElements = new ArrayList<>();
		
		while(!currentFlowchart.getType().equals(STOP)) {
			
			if(isLoop && currentFlowchart.equals(loopStartFlowchart)) break;

			if(currentFlowchart.getType().equals(ASSIGMENT)) {
				codeElements.addAll(convertToAssigmentElements(currentFlowchart));
			} 
			else if(currentFlowchart.getType().equals(OPERATION)) {
				codeElements.add(convertToOperationElement(currentFlowchart));
			}
			else if(currentFlowchart.getType().equals(OUTPUT)) {
				codeElements.add(convertToOutputElement(currentFlowchart));
			}
			else if(currentFlowchart.getType().equals(DECISION)) {
				
				if(!checkIfLoop(currentFlowchart)) {
					codeElements.add(convertToIfElseElement(currentFlowchart));
					break;
				}
				else {
					isLoop = true;
					codeElements.add(convertToLoop(currentFlowchart));
					isLoop = false;
				}
				
			}
			
			currentFlowchart = currentFlowchart.getType().equals(DECISION) && checkIfLoop(currentFlowchart) ? flowchartFinder.goLeft(currentFlowchart)
					                                                                                        : flowchartFinder.findNextFlowchart(currentFlowchart);
		}
	
		return codeElements;
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
		
		cachedAssigmentElements.addAll(elements);
		
		return elements;
	}
	
	private CodeElement convertToLoop(Flowchart loopFlowchart) {
	
		List<Flowchart> loopFlowcharts = getLoopFlowcharts(loopFlowchart);
		
		DecisionFlowchart decisionFlowchart = (DecisionFlowchart) loopFlowcharts.stream().filter(flowchart -> flowchart.getType().equals(DECISION)).findAny().get();
		
		String iteratorName = decisionFlowchart.getLeftVariable().getText();
		
		String iteratorStartValue = cachedAssigmentElements.stream().filter(element -> element.getCodeElementType().equals(ASSIGMENT_ELEMENT))
				                                                    .filter(element -> ((AssigmentElement) element).getVariableName().equals(iteratorName))
				                                                    .map(element -> ((AssigmentElement) element).getVariableValue())
				                                                    .findAny().get();
		
		String conditionSign = decisionFlowchart.getOptions().getSelectedItem().toString();
		String conditionValue = decisionFlowchart.getRightVariable().getText();
		
		OperationFlowchart iteratorOperationFlowchart = (OperationFlowchart) loopFlowcharts.stream().filter(flowchart -> flowchart.getType().equals(OPERATION))
				                                                                                    .filter(flowchart -> ((OperationFlowchart) flowchart).getAssigmentVariable().getText().equals(iteratorName))
				                                                                                    .findAny().orElse(null);
		
		loopStartFlowchart = decisionFlowchart;
		
		if(iteratorOperationFlowchart != null) {
			ForLoopElement forLoop = new ForLoopElement();
			
			String iteratorChangeSign = iteratorOperationFlowchart.getOperations().getSelectedItem().toString();
			String iteratorChangeValue = iteratorOperationFlowchart.getSecondVariable().getText();
			
			forLoop.setIteratorName(iteratorName);
			forLoop.setIteratorStartValue(iteratorStartValue);
			forLoop.setConditionSign(conditionSign);
			forLoop.setConditionValue(conditionValue);
			forLoop.setIteratorChangeSign(iteratorChangeSign);
			forLoop.setIteratorChangeValue(iteratorChangeValue);
			
			Flowchart nextToIteratorFlowchart = flowchartFinder.findNextFlowchart(iteratorOperationFlowchart);
			List<CodeElement> forLoopElements = convertToCodeElements(nextToIteratorFlowchart);
			forLoop.setCodeElements(forLoopElements);
			
			return forLoop;
		}
		else {
			WhileLoopElement whileLoop = new WhileLoopElement();
			
			whileLoop.setLeftConditionVariable(iteratorName);
			whileLoop.setRightConditionVariable(conditionValue);
			whileLoop.setConditionSign(conditionSign);
			
			Flowchart nextToDecisionFlowchart = flowchartFinder.findNextFlowchart(decisionFlowchart);
			List<CodeElement> whileLoopElements = convertToCodeElements(nextToDecisionFlowchart);
			whileLoop.setCodeElements(whileLoopElements);
			
			return whileLoop;
		}
	}
	
	private CodeElement convertToIfElseElement(Flowchart flowchart) {
		IfElseElement ifElseElement = new IfElseElement((DecisionFlowchart)flowchart);
		
		Flowchart leftFlowchart = flowchartFinder.goLeft(flowchart);
		
		List<CodeElement> passedCodeElements = convertToCodeElements(leftFlowchart);
		
		ifElseElement.setPassedConditionElements(passedCodeElements);
		
		Flowchart rightFlowchart = flowchartFinder.goRight(flowchart);
		
		List<CodeElement> failedCodeElements = convertToCodeElements(rightFlowchart);
		
		ifElseElement.setFailedConditionElements(failedCodeElements);
		
		return ifElseElement;
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
		Flowchart nextOnRightFlowchart = flowchartFinder.goRight(flowchart);
		
		while(true) {
			
			if(!nextOnRightFlowchart.isConnector()) {
				if(nextOnRightFlowchart.getType().equals(STOP)) return false;
				else if(nextOnRightFlowchart.equals(flowchart)) return true;
			}
			
			nextOnRightFlowchart = nextOnRightFlowchart.getType().equals(DECISION) ? flowchartFinder.goLeft(nextOnRightFlowchart)
					                                                               : flowchartFinder.findNextFlowchart(nextOnRightFlowchart);
		}
	}
	
	private List<Flowchart> getLoopFlowcharts(Flowchart flowchart) {
		List<Flowchart> loopFlowcharts = new ArrayList<>();
		Flowchart nextLoopFlowchart = flowchartFinder.goRight(flowchart);
		
		loopFlowcharts.add(flowchart);
		
		while(!nextLoopFlowchart.equals(flowchart)) {
			loopFlowcharts.add(nextLoopFlowchart);
			nextLoopFlowchart = flowchartFinder.findNextFlowchart(nextLoopFlowchart);
		}
		
		return loopFlowcharts;
	}
}
