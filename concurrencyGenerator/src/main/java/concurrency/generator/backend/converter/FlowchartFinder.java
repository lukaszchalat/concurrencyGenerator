package concurrency.generator.backend.converter;

import static concurrency.generator.frontend.enums.FlowchartEnum.START;
import static concurrency.generator.frontend.enums.ConnectorEnum.*;

import java.util.List;
import java.util.Optional;

import concurrency.generator.frontend.enums.ConnectorEnum;
import concurrency.generator.frontend.flowchart.Flowchart;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;
import concurrency.generator.frontend.flowchart.Connector;

public class FlowchartFinder {

	private List<Flowchart> flowcharts;
	private final String TOP_DIRECTION = "TOP";
	private final String RIGHT_DIRECTION = "RIGHT";
	private final String BOTTOM_DIRECTION = "BOTTOM";
	private final String LEFT_DIRECTION = "LEFT";

	public FlowchartFinder(List<Flowchart> flowcharts) {
		this.flowcharts = flowcharts;
	}
	
	public Flowchart findStartFlowchart() {
		return flowcharts.stream().filter(flowchart -> flowchart.getType().equals(START)).findFirst().get();
	}

	public Flowchart findNextFlowchart(Flowchart flowchart) {
		MatrixCoordinates coordinates = flowchart.getCoordinates();
		int column = coordinates.getColumn();
		int row = coordinates.getRow();
		Optional<Flowchart> nextFlowchart;
		
		if(!flowchart.isConnector()) {
		
			nextFlowchart = check(TOP_DIRECTION, row - 1, column);
			
			if(!nextFlowchart.isPresent()) {
				nextFlowchart = check(RIGHT_DIRECTION, row, column + 1);
			}
			
			if(!nextFlowchart.isPresent()) {
				nextFlowchart = check(BOTTOM_DIRECTION, row + 1, column);
			}
			
			if(!nextFlowchart.isPresent()) {
				nextFlowchart = check(LEFT_DIRECTION, row, column - 1);
			}
	
			return nextFlowchart.isPresent() ? nextFlowchart.get() : null;
		
		} else {
			ConnectorEnum type = ((Connector) flowchart).getConnectorType();
			
			switch (type) {
				case TOP:
					return findByCoordinates(new MatrixCoordinates(row - 1, column)).get();
				case TOP_RIGHT:
					return findByCoordinates(new MatrixCoordinates(row - 1, column + 1)).get();
				case TOP_LEFT:
					return findByCoordinates(new MatrixCoordinates(row - 1, column - 1)).get();
				case BOTTOM:
					return findByCoordinates(new MatrixCoordinates(row + 1, column)).get();
				case BOTTOM_RIGHT:
					return findByCoordinates(new MatrixCoordinates(row + 1, column + 1)).get();
				case BOTTOM_LEFT:
					return findByCoordinates(new MatrixCoordinates(row + 1, column - 1)).get();
				case RIGHT:
					return findByCoordinates(new MatrixCoordinates(row, column + 1)).get();
				case LEFT:
					return findByCoordinates(new MatrixCoordinates(row, column - 1)).get();
				default:
					return null;
			}
		}
	}

	public Optional<Flowchart> check(String direction, int row, int column) {
		Optional<Flowchart> nextFlowchart = findByCoordinates(new MatrixCoordinates(row, column));
		
		if(!nextFlowchart.isPresent()) return Optional.empty();
		
		switch(direction) {
			case TOP_DIRECTION:
				return checkTop(nextFlowchart.get());
			case RIGHT_DIRECTION:
				return checkRight(nextFlowchart.get());
			case BOTTOM_DIRECTION:
				return checkBottom(nextFlowchart.get());
			case LEFT_DIRECTION:
				return checkLeft(nextFlowchart.get());
			default:
				return Optional.empty();
		}
	}
	
	public Flowchart goLeft(Flowchart flowchart) {
		MatrixCoordinates coordinates = flowchart.getCoordinates();
		int column = coordinates.getColumn();
		int row = coordinates.getRow();
		
		return findByCoordinates(new MatrixCoordinates(row, column - 1)).get();
	}
	
	public Flowchart goRight(Flowchart flowchart) {
		MatrixCoordinates coordinates = flowchart.getCoordinates();
		int column = coordinates.getColumn();
		int row = coordinates.getRow();
		
		return findByCoordinates(new MatrixCoordinates(row, column + 1)).get();
	}
	
	private Optional<Flowchart> checkTop(Flowchart flowchart) {
		ConnectorEnum connectorType = flowchart.isConnector() ? ((Connector) flowchart).getConnectorType() : null;
		
		if(connectorType.equals(TOP) || connectorType.equals(TOP_LEFT) || connectorType.equals(TOP_RIGHT)) {
			return Optional.of(flowchart);
		} else {
			return Optional.empty();
		}
	}
	
	private Optional<Flowchart> checkRight(Flowchart flowchart) {
		ConnectorEnum connectorType = flowchart.isConnector() ? ((Connector) flowchart).getConnectorType() : null;
		
		if(connectorType.equals(RIGHT) || connectorType.equals(TOP_RIGHT) || connectorType.equals(BOTTOM_RIGHT)) {
			return Optional.of(flowchart);
		} else {
			return Optional.empty();
		}
	}
	
	private Optional<Flowchart> checkBottom(Flowchart flowchart) {
		ConnectorEnum connectorType = flowchart.isConnector() ? ((Connector) flowchart).getConnectorType() : null;
		
		if(connectorType.equals(BOTTOM) || connectorType.equals(BOTTOM_RIGHT) || connectorType.equals(BOTTOM_LEFT)) {
			return Optional.of(flowchart);
		} else {
			return Optional.empty();
		}
	}
	
	private Optional<Flowchart> checkLeft(Flowchart flowchart) {
		ConnectorEnum connectorType = flowchart.isConnector() ? ((Connector) flowchart).getConnectorType() : null;
		
		if(connectorType.equals(LEFT) || connectorType.equals(TOP_LEFT) || connectorType.equals(BOTTOM_LEFT)) {
			return Optional.of(flowchart);
		} else {
			return Optional.empty();
		}
	}

	public Optional<Flowchart> findByCoordinates(MatrixCoordinates coordinates) {
		return flowcharts.stream().filter(flowchart -> flowchart.getCoordinates().equals(coordinates)).findFirst();
	}
}
