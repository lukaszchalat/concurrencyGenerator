package concurrency.generator.frontend.gui;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;
import static concurrency.generator.frontend.enums.ConnectorEnum.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import concurrency.generator.frontend.enums.AlgorithmEnum;
import concurrency.generator.frontend.enums.ConnectorEnum;
import concurrency.generator.frontend.enums.TechnologyEnum;
import concurrency.generator.frontend.flowchart.Flowchart;
import concurrency.generator.frontend.flowchart.FlowchartBuilder;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;

public class GUICreator {
	
	public static List<Flowchart> createFlowchartMatrix() {
		
		Flowchart flowchartMatrix[][] = new Flowchart[FLOWCHART_MATRIX_WIDTH][FLOWCHART_MATRIX_HEIGHT];
		int coordinateX = 0;
		int coordinateY = 0;

		for (int i = 0; i < FLOWCHART_MATRIX_WIDTH; i++) {

			for (int j = 0; j < FLOWCHART_MATRIX_HEIGHT; j++) {
				CoordinatesXY coordinatesXY = new CoordinatesXY(coordinateX, coordinateY);
				MatrixCoordinates matrixCoordinates = new MatrixCoordinates(i, j);
				flowchartMatrix[i][j] = new FlowchartBuilder().coordinatesXY(coordinatesXY).matrixCoordinates(matrixCoordinates).flowchartType(EMPTY).build();
				coordinateX += FLOW_CHART_SIZE;
			}
			coordinateX = 0;
			coordinateY += FLOW_CHART_SIZE;
		}
		
		return GUIUtilities.convertToList(flowchartMatrix);
	}
	
	public static List<Flowchart> createFlowcharts() {
		List<Flowchart> flowcharts = new ArrayList<>();
		
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(825, 500)).flowchartType(START).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(950, 500)).flowchartType(ASSIGMENT).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(1075, 500)).flowchartType(DECISION).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(825, 650)).flowchartType(OPERATION).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(950, 650)).flowchartType(OUTPUT).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(1075, 650)).flowchartType(STOP).build());
		flowcharts.add(new FlowchartBuilder().coordinatesXY(new CoordinatesXY(1075, 350)).flowchartType(CONNECTOR).connectorType(RIGHT).build());
		
		return flowcharts;
	}
	
	public static JLabel createConnectorLabel() {
		JLabel connectorLabel = new JLabel("Select connector:", JLabel.LEFT);
		connectorLabel.setBounds(825, 350, LABEL_DROPDOWN__WIDTH, LABEL_DROPDOWN_HEIGHT);
		
		return connectorLabel;
	}
	
	public static JComboBox<String> createConnectorDropdown() {
		String[] directories = new String[ConnectorEnum.getAllDirectories().size()];
		directories = ConnectorEnum.getAllDirectories().toArray(directories);
		JComboBox<String> connectorDropdown = new JComboBox<String>(directories);
		
		connectorDropdown.setBounds(950, 350, DROPDOWN_WIDTH, DROPDOWN_HEIGHT);
		
		return connectorDropdown;
	}
	
	public static JLabel createTechnologyLabel() {
		JLabel technologyLabel = new JLabel("Select technology:", JLabel.LEFT);
		technologyLabel.setBounds(825, 300, LABEL_DROPDOWN__WIDTH, LABEL_DROPDOWN_HEIGHT);
		
		return technologyLabel;
	}
	
	public static JComboBox<String> createTechnologyDropdown() {
		String[] names = new String[TechnologyEnum.getAllNames().size()];
		names = TechnologyEnum.getAllNames().toArray(names);
		JComboBox<String> technologyDropdown = new JComboBox<String>(names);
		
		technologyDropdown.setBounds(950, 300, DROPDOWN_WIDTH, DROPDOWN_HEIGHT);
		
		return technologyDropdown;
	}
	
	public static JLabel createAlgorithmLabel() {
		JLabel algorithmLabel = new JLabel("Select algorithm:", JLabel.LEFT);
		algorithmLabel.setBounds(825, 250, LABEL_DROPDOWN__WIDTH, LABEL_DROPDOWN_HEIGHT);
		
		return algorithmLabel;
	}
	
	public static JComboBox<String> createAlgorithmDropDown() {
		String[] names = new String[AlgorithmEnum.getAllNames().size()];
		names = AlgorithmEnum.getAllNames().toArray(names);
		JComboBox<String> algorithmDropDown = new JComboBox<String>(names);
		
		algorithmDropDown.setBounds(950, 250, DROPDOWN_WIDTH, DROPDOWN_HEIGHT);
		
		return algorithmDropDown;
	}
	
	public static JButton createClearAllButton() {
		JButton clearAllButton = new JButton("Clear All");
		clearAllButton.setBounds(950, 200, 100, 25);
		
		return clearAllButton;
	}
	
	public static JButton createClearButton() {
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(1075, 200, 100, 25);
		
		return clearButton;
	}
	
	public static JButton createStartButton() {
		JButton startButton = new JButton("Start");
		startButton.setBounds(825, 200, 100, 25);
		
		return startButton;
	}
	
	public static JButton createFileChooserButton() {
		JButton fileChooserButton = new JButton("Choose target directory");
		fileChooserButton.setBounds(825, 100, 225, 25);
		
		return fileChooserButton;
	}
	
	public static JFileChooser createFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Choose target directory for generated code");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		return fileChooser;
	}
	
	public static JLabel createChoosenDirectoryLabel() {
		JLabel choosenDirectoryLabel = new JLabel("Selected directory:", JLabel.LEFT);
		choosenDirectoryLabel.setBounds(825, 150, 400, LABEL_DROPDOWN_HEIGHT);
		
		return choosenDirectoryLabel;
	}
}
