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
	private static List<Flowchart> euklidesAlgorithmWithSubstraction;
	private static List<Flowchart> euklidesAlgorithmWithDivision;
	private static List<Flowchart> sumOfNaturalNumbersAlgoritm;
	
	public static void init() {
		setFibonacciAlgorithm();
		setEuklidesAlgorithmWithDivision();
		setEuklidesAlgorithmWithSubstraction();
		setSumOfNaturalNumbersAlgoritm();
	}
	
	public static List<Flowchart> getAlgorithm(String algorithm) {
		switch(algorithm) {
			case "Fibonacci":
				return fibonacciAlgorithm;
			case "Euklides with substraction":
				return euklidesAlgorithmWithSubstraction;
			case "Euklides with division":
				return euklidesAlgorithmWithDivision;
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
	
	private static void setEuklidesAlgorithmWithDivision() {
		euklidesAlgorithmWithDivision = new ArrayList<>();
		
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(START).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 100)).matrixCoordinates(new MatrixCoordinates(1, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(ASSIGMENT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 300)).matrixCoordinates(new MatrixCoordinates(3, 3)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 400)).matrixCoordinates(new MatrixCoordinates(4, 3)).flowchartType(DECISION).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(OPERATION).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(ASSIGMENT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 600)).matrixCoordinates(new MatrixCoordinates(6, 4)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 600)).matrixCoordinates(new MatrixCoordinates(6, 3)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 500)).matrixCoordinates(new MatrixCoordinates(5, 3)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 400)).matrixCoordinates(new MatrixCoordinates(4, 2)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 400)).matrixCoordinates(new MatrixCoordinates(4, 1)).flowchartType(OUTPUT).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 500)).matrixCoordinates(new MatrixCoordinates(5, 1)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithDivision.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 600)).matrixCoordinates(new MatrixCoordinates(6, 1)).flowchartType(STOP).build());
	}
	
	private static void setEuklidesAlgorithmWithSubstraction() {
		euklidesAlgorithmWithSubstraction = new ArrayList<>();
		
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 0)).matrixCoordinates(new MatrixCoordinates(0, 2)).flowchartType(START).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 0)).matrixCoordinates(new MatrixCoordinates(0, 3)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 0)).matrixCoordinates(new MatrixCoordinates(0, 4)).flowchartType(ASSIGMENT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 100)).matrixCoordinates(new MatrixCoordinates(1, 4)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 200)).matrixCoordinates(new MatrixCoordinates(2, 4)).flowchartType(DECISION).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(300, 200)).matrixCoordinates(new MatrixCoordinates(2, 3)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(200, 200)).matrixCoordinates(new MatrixCoordinates(2, 2)).flowchartType(OUTPUT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(100, 200)).matrixCoordinates(new MatrixCoordinates(2, 1)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(0, 200)).matrixCoordinates(new MatrixCoordinates(2, 0)).flowchartType(STOP).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 200)).matrixCoordinates(new MatrixCoordinates(2, 5)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 200)).matrixCoordinates(new MatrixCoordinates(2, 6)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 300)).matrixCoordinates(new MatrixCoordinates(3, 6)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 400)).matrixCoordinates(new MatrixCoordinates(4, 6)).flowchartType(DECISION).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 400)).matrixCoordinates(new MatrixCoordinates(4, 5)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 400)).matrixCoordinates(new MatrixCoordinates(4, 7)).flowchartType(CONNECTOR).connectorType(BOTTOM).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 500)).matrixCoordinates(new MatrixCoordinates(5, 5)).flowchartType(OPERATION).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 500)).matrixCoordinates(new MatrixCoordinates(5, 7)).flowchartType(OPERATION).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(700, 600)).matrixCoordinates(new MatrixCoordinates(6, 7)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(600, 600)).matrixCoordinates(new MatrixCoordinates(6, 6)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(500, 600)).matrixCoordinates(new MatrixCoordinates(6, 5)).flowchartType(CONNECTOR).connectorType(LEFT).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 600)).matrixCoordinates(new MatrixCoordinates(6, 4)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 500)).matrixCoordinates(new MatrixCoordinates(5, 4)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 400)).matrixCoordinates(new MatrixCoordinates(4, 4)).flowchartType(CONNECTOR).connectorType(TOP).build());
		euklidesAlgorithmWithSubstraction.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(400, 300)).matrixCoordinates(new MatrixCoordinates(3, 4)).flowchartType(CONNECTOR).connectorType(TOP).build());
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
