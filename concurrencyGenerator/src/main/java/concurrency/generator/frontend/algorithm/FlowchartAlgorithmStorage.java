package concurrency.generator.frontend.algorithm;

import static concurrency.generator.frontend.enums.ConnectorEnum.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.util.ArrayList;
import java.util.List;

import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;
import concurrency.generator.frontend.flowcharts.Flowchart;
import concurrency.generator.frontend.flowcharts.FlowchartBuilder;

public class FlowchartAlgorithmStorage {

	private static List<Flowchart> fibonacciAlgorithm;
	private static List<Flowchart> euklidesAlgorithm;
	private static List<Flowchart> sumOfNaturalNumbersAlgoritm;
	
	public static void init() {
		setFibonacciAlgorithm();
		setEuklidesAlgorithm();
		setSumOfNaturalNumbersAlgoritm();
	}
	
	public static List<Flowchart> getAlgorithm(String algorithm) {
		switch(algorithm) {
			case "Fibonacci":
				return fibonacciAlgorithm;
			case "Euklides":
				return euklidesAlgorithm;
			case "Sum of numbers":
				return sumOfNaturalNumbersAlgoritm;
			default:
				return new ArrayList<>();
		}
	}
	
	private static void setFibonacciAlgorithm() {
		fibonacciAlgorithm = new ArrayList<>();
		
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(0, 0)).matrixCoordinates(new MatrixCoordinates(0, 0)).flowchartType(START).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 0)).matrixCoordinates(new MatrixCoordinates(0, 1)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 0)).matrixCoordinates(new MatrixCoordinates(0, 2)).flowchartType(ASSIGMENT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 0)).matrixCoordinates(new MatrixCoordinates(0, 4)).flowchartType(ASSIGMENT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 100)).matrixCoordinates(new MatrixCoordinates(1, 4)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 200)).matrixCoordinates(new MatrixCoordinates(2, 4)).flowchartType(DECISION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 200)).matrixCoordinates(new MatrixCoordinates(2, 2)).flowchartType(OUTPUT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 200)).matrixCoordinates(new MatrixCoordinates(2, 1)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(0, 200)).matrixCoordinates(new MatrixCoordinates(2, 0)).flowchartType(STOP).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 200)).matrixCoordinates(new MatrixCoordinates(2, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 300)).matrixCoordinates(new MatrixCoordinates(3, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(DECISION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 400)).matrixCoordinates(new MatrixCoordinates(4, 3)).flowchartType(OUTPUT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 400)).matrixCoordinates(new MatrixCoordinates(4, 2)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 400)).matrixCoordinates(new MatrixCoordinates(4, 1)).flowchartType(STOP).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 400)).matrixCoordinates(new MatrixCoordinates(4, 6)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 400)).matrixCoordinates(new MatrixCoordinates(4, 7)).flowchartType(OPERATION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 500)).matrixCoordinates(new MatrixCoordinates(5, 7)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 600)).matrixCoordinates(new MatrixCoordinates(6, 7)).flowchartType(OPERATION).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 600)).matrixCoordinates(new MatrixCoordinates(6, 6)).flowchartType(CONNECTOR).connectorType(LEFT).build());	
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(ASSIGMENT).build());
		fibonacciAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(CONNECTOR).connectorType(TOP).build());
	}
	
	private static void setEuklidesAlgorithm() {
		euklidesAlgorithm = new ArrayList<>();
		
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(START).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 100)).matrixCoordinates(new MatrixCoordinates(1, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(ASSIGMENT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 300)).matrixCoordinates(new MatrixCoordinates(3, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 400)).matrixCoordinates(new MatrixCoordinates(4, 3)).flowchartType(DECISION).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(OPERATION).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(ASSIGMENT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 600)).matrixCoordinates(new MatrixCoordinates(6, 4)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 600)).matrixCoordinates(new MatrixCoordinates(6, 3)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 500)).matrixCoordinates(new MatrixCoordinates(5, 3)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 400)).matrixCoordinates(new MatrixCoordinates(4, 2)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 400)).matrixCoordinates(new MatrixCoordinates(4, 1)).flowchartType(OUTPUT).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 500)).matrixCoordinates(new MatrixCoordinates(5, 1)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 600)).matrixCoordinates(new MatrixCoordinates(6, 1)).flowchartType(STOP).build());
	}
	
	private static void setSumOfNaturalNumbersAlgoritm() {
		sumOfNaturalNumbersAlgoritm = new ArrayList<>();
		
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(START).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 100)).matrixCoordinates(new MatrixCoordinates(1, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(ASSIGMENT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 200)).matrixCoordinates(new MatrixCoordinates(2, 4)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 200)).matrixCoordinates(new MatrixCoordinates(2, 5)).flowchartType(ASSIGMENT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 300)).matrixCoordinates(new MatrixCoordinates(3, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(DECISION).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 400)).matrixCoordinates(new MatrixCoordinates(4, 3)).flowchartType(OUTPUT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 400)).matrixCoordinates(new MatrixCoordinates(4, 2)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 400)).matrixCoordinates(new MatrixCoordinates(4, 1)).flowchartType(STOP).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 400)).matrixCoordinates(new MatrixCoordinates(4, 6)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 400)).matrixCoordinates(new MatrixCoordinates(4, 7)).flowchartType(OPERATION).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 500)).matrixCoordinates(new MatrixCoordinates(5, 7)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 600)).matrixCoordinates(new MatrixCoordinates(6, 7)).flowchartType(OPERATION).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 600)).matrixCoordinates(new MatrixCoordinates(6, 6)).flowchartType(CONNECTOR).connectorType(LEFT).build());	
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(OPERATION).build());
		sumOfNaturalNumbersAlgoritm.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(CONNECTOR).connectorType(TOP).build());
	}
}
