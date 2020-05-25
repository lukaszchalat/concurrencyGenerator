package concurrency.generator.frontend.flowchart;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import javax.swing.JLabel;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class StartFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StartFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, START, coordinates);
		this.setText("START");
		this.setHorizontalAlignment(JLabel.CENTER);
	}
}
