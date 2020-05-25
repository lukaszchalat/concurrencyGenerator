package concurrency.generator.frontend.flowchart;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class AssigmentFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2370399361872978978L;
	
	private Map<JTextField, JTextField> assigmentMapping;
	
	private int assigmentsAmount = 3;
	
	public AssigmentFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, ASSIGMENT, coordinates);
		this.setText("ASSIGMENT");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		
		assigmentMapping = new HashMap<>();
		
		JTextField variableName, variableValue;
		JLabel equalitySign;
		int height = 20;
		
		for(int i = 0; i < assigmentsAmount; i++) {
			variableName = new JTextField();
			variableName.setBounds(5, height, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
			
			variableValue = new JTextField();
			variableValue.setBounds(65, height, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
			
			equalitySign = new JLabel("=", JLabel.CENTER);
			equalitySign.setBounds(35, height, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
			
			this.add(variableName);
			this.add(variableValue);
			this.add(equalitySign);
			
			assigmentMapping.put(variableName, variableValue);
			height += 25;
		}
	}

	public Map<JTextField, JTextField> getAssigmentMapping() {
		return assigmentMapping;
	}
}
