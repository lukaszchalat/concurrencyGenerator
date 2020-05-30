package concurrency.generator.frontend.algorithm;

import static concurrency.generator.frontend.enums.ConnectorEnum.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.util.ArrayList;
import java.util.List;

import concurrency.generator.frontend.flowchart.Flowchart;
import concurrency.generator.frontend.flowchart.FlowchartBuilder;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class FlowchartAlgorithmStorage {

	private static List<Flowchart> fibonacciAlgorithm;
	
	public static void init() {
		setFibonacciAlgorithm();
	}
	
	private static void setFibonacciAlgorithm() {
		fibonacciAlgorithm = new ArrayList<>();
		
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(START).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 100)).matrixCoordinates(new MatrixCoordinates(1, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(ASSIGMENT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 300)).matrixCoordinates(new MatrixCoordinates(3, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 400)).matrixCoordinates(new MatrixCoordinates(4, 3)).flowchartType(DECISION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 400)).matrixCoordinates(new MatrixCoordinates(4, 2)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 400)).matrixCoordinates(new MatrixCoordinates(4, 1)).flowchartType(STOP).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(OPERATION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(ASSIGMENT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 600)).matrixCoordinates(new MatrixCoordinates(6, 4)).flowchartType(CONNECTOR).connectorType(LEFT).build());	
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 600)).matrixCoordinates(new MatrixCoordinates(6, 3)).flowchartType(CONNECTOR).connectorType(TOP_LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 500)).matrixCoordinates(new MatrixCoordinates(5, 3)).flowchartType(CONNECTOR).connectorType(TOP).build());
	}

	public static List<Flowchart> getFibonacciAlgorithm() {
		return fibonacciAlgorithm;
	}
	
}