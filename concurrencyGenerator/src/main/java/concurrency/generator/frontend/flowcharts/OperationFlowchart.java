package concurrency.generator.frontend.flowcharts;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class OperationFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 716891362945365555L;
	
	private JTextField assigmentVariable;
	
	private JTextField firstVariable;
	
	private JTextField secondVariable;
	
	private JComboBox<String> operations;
	
	private JLabel assigmentSign; 
	
	public OperationFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, OPERATION, coordinates);
		this.setText("OPERATION");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		
		assigmentVariable = new JTextField();
		assigmentVariable.setBounds(30, 30, INPUT_TEXT_SMALL_WIDTH, INPUT_TEXT_HEIGHT);
		
		assigmentSign = new JLabel("=", JLabel.CENTER);
		assigmentSign.setBounds(55, 30, LABEL_SIGN_WIDTH, LABEL_SIGN_HEIGHT);
		
		firstVariable = new JTextField();
		firstVariable.setBounds(10, 60, INPUT_TEXT_SMALL_WIDTH, INPUT_TEXT_HEIGHT);
		
		String operationsValues[] = {"+", "-", "*", "/", "%"};
		
		operations = new JComboBox<String>(operationsValues);
		operations.setBounds(32, 60, COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
		
		secondVariable = new JTextField();
		secondVariable.setBounds(75, 60, INPUT_TEXT_SMALL_WIDTH, INPUT_TEXT_HEIGHT);
		
		this.add(assigmentVariable);
		this.add(assigmentSign);
		this.add(firstVariable);
		this.add(operations);
		this.add(secondVariable);
	}

	public JTextField getAssigmentVariable() {
		return assigmentVariable;
	}

	public JTextField getFirstVariable() {
		return firstVariable;
	}

	public JTextField getSecondVariable() {
		return secondVariable;
	}

	public JComboBox<String> getOperations() {
		return operations;
	}
}
