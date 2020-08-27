package concurrency.generator.frontend.flowchart;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class DecisionFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038203844613810397L;
	
    private JTextField leftVariable;
	
	private JTextField rightVariable;
	
	private JComboBox<String> options;
	
	public DecisionFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, DECISION, coordinates);
		this.setText("DECISION");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		
		leftVariable = new JTextField();
		leftVariable.setBounds(35, 25, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
		
		rightVariable = new JTextField();
		rightVariable.setBounds(35, 75, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
		
		String optionsValues[] = {">", "<", ">=", "<=", "==", "!=", "%"};
		
		options = new JComboBox<String>(optionsValues);
		options.setBounds(30, 50, COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
		
		this.add(leftVariable);
		this.add(rightVariable);
		this.add(options);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getLeftVariable() {
		return leftVariable;
	}

	public JTextField getRightVariable() {
		return rightVariable;
	}

	public JComboBox<String> getOptions() {
		return options;
	}
}
