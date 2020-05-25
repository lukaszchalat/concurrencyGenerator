package concurrency.generator.frontend.flowchart;

import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import concurrency.generator.frontend.enums.ConnectorEnum;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;;

public class Connector extends Flowchart {

	private ConnectorEnum connectorType;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5988875103394963003L;
	
	public Connector(CoordinatesXY coordinatesXY, ConnectorEnum connectorType, MatrixCoordinates coordinates) {
		super(coordinatesXY, CONNECTOR, coordinates);
		this.connectorType = connectorType;
	}

	public ConnectorEnum getConnectorType() {
		return connectorType;
	}
	
	public void setConnectorType(ConnectorEnum connectorType) {
		this.connectorType = connectorType;
	}
}
