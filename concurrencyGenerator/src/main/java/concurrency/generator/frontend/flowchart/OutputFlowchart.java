package concurrency.generator.frontend.flowchart;

import static concurrency.generator.frontend.configuration.ConfigurationValues.INPUT_TEXT_HEIGHT;
import static concurrency.generator.frontend.configuration.ConfigurationValues.INPUT_TEXT_WIDTH;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import javax.swing.JLabel;
import javax.swing.JTextField;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class OutputFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991455802721971914L;
	
	private JTextField outputVariable;
	
	public OutputFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, OUTPUT, coordinates);
		this.setText("OUTPUT");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		
		outputVariable = new JTextField();
		outputVariable.setBounds(35, 50, INPUT_TEXT_WIDTH, INPUT_TEXT_HEIGHT);
		
		this.add(outputVariable);
	}

	public JTextField getOutputVariable() {
		return outputVariable;
	}

}
