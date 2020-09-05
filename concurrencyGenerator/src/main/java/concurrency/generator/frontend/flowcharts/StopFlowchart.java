package concurrency.generator.frontend.flowcharts;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import javax.swing.JLabel;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class StopFlowchart extends Flowchart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5757123006395104798L;
	
	public StopFlowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		super(coordinatesXY, STOP, coordinates);
		this.setText("STOP");
		this.setHorizontalAlignment(JLabel.CENTER);
	}

}
