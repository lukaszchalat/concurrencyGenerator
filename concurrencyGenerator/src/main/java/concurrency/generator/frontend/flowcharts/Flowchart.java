package concurrency.generator.frontend.flowcharts;

import static concurrency.generator.frontend.configuration.ConfigurationValues.FLOW_CHART_SIZE;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import concurrency.generator.frontend.enums.FlowchartEnum;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class Flowchart extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FlowchartEnum type;
	
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	
	private MatrixCoordinates coordinates;
	
	private CoordinatesXY coordinatesXY;
	
	public Flowchart(CoordinatesXY coordinatesXY, MatrixCoordinates coordinates) {
		this(coordinatesXY, EMPTY, coordinates);
	}
	
	public Flowchart(CoordinatesXY coordinatesXY, FlowchartEnum type, MatrixCoordinates coordinates) {
		this.type = type;
		this.coordinates = coordinates;
		this.coordinatesXY = coordinatesXY;
		this.setBounds(coordinatesXY.getX(), coordinatesXY.getY(), FLOW_CHART_SIZE, FLOW_CHART_SIZE);
		this.setBorder(border);
	}
	
	public FlowchartEnum getType() {
		return type;
	}

	public MatrixCoordinates getCoordinates() {
		return coordinates;
	}	
	
	public CoordinatesXY getCoordinatesXY() {
		return this.coordinatesXY;
	}
	
	public void setImage(String imagePath) {
		this.setIcon(new ImageIcon(Connector.class.getResource(imagePath)));
	}
	
	public boolean isNotEmpty() {
		return ! type.equals(EMPTY);
	}
	
	public boolean isConnector() {
		return type.equals(CONNECTOR);
	}
	
	public int getColumn() {
		return this.coordinates.getColumn();
	}
	
	public int getRow() {
		return this.coordinates.getRow();
	}

	@Override
	public String toString() {
		return "Flowchart [type=" + type + ", border=" + border + ", coordinates=" + coordinates + ", coordinatesXY="
				+ coordinatesXY + "]";
	}
}
