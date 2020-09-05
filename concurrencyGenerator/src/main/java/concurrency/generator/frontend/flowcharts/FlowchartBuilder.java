package concurrency.generator.frontend.flowcharts;

import concurrency.generator.frontend.enums.ConnectorEnum;
import concurrency.generator.frontend.enums.FlowchartEnum;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class FlowchartBuilder {
	
	private CoordinatesXY coordinatesXY;
	
	private MatrixCoordinates matrixCoordinates;
	
	private FlowchartEnum flowchartType;
	
	private ConnectorEnum connectorType;
	
	public FlowchartBuilder() {
		
	}
	
	public FlowchartBuilder coordinatesXY(CoordinatesXY coordinatesXY) {
		this.coordinatesXY = coordinatesXY;
		return this;
	}
	
	public FlowchartBuilder matrixCoordinates(MatrixCoordinates matrixCoordinates) {
		this.matrixCoordinates = matrixCoordinates;
		return this;
	}
	
	public FlowchartBuilder flowchartType(FlowchartEnum flowchartType) {
		this.flowchartType = flowchartType;
		return this;
	}
	
	public FlowchartBuilder connectorType(ConnectorEnum connectorType) {
		this.connectorType = connectorType;
		return this;
	}
	
	public Flowchart build() {
		
		switch (flowchartType) {
		case EMPTY:
			return new Flowchart(coordinatesXY, matrixCoordinates);
		case START:
			return new StartFlowchart(coordinatesXY, matrixCoordinates);
		case STOP:
			return new StopFlowchart(coordinatesXY, matrixCoordinates);
		case ASSIGMENT:
			return new AssigmentFlowchart(coordinatesXY, matrixCoordinates);
		case DECISION:
			return new DecisionFlowchart(coordinatesXY, matrixCoordinates);
		case OUTPUT:
			return new OutputFlowchart(coordinatesXY, matrixCoordinates);
		case OPERATION:
			return new OperationFlowchart(coordinatesXY, matrixCoordinates);
		case CONNECTOR:
			Connector connector = new Connector(coordinatesXY, connectorType, matrixCoordinates);
			connector.setImage(connectorType.getPath());
			return connector;
		default:
			return null;
		}
	}

}
