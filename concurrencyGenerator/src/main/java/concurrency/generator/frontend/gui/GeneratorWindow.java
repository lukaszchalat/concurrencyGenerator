package concurrency.generator.frontend.gui;

import static concurrency.generator.frontend.configuration.ConfigurationValues.*;
import static concurrency.generator.frontend.enums.FlowchartEnum.CONNECTOR;
import static concurrency.generator.frontend.enums.FlowchartEnum.EMPTY;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import concurrency.generator.backend.code.CodeElement;
import concurrency.generator.backend.code.generator.SourceCodeGeneratorController;
import concurrency.generator.backend.converter.CodeConverter;
import concurrency.generator.frontend.algorithm.FlowchartAlgorithmStorage;
import concurrency.generator.frontend.enums.ConnectorEnum;
import concurrency.generator.frontend.enums.FlowchartEnum;
import concurrency.generator.frontend.flowchart.Connector;
import concurrency.generator.frontend.flowchart.Flowchart;
import concurrency.generator.frontend.flowchart.FlowchartBuilder;
import concurrency.generator.frontend.flowchart.util.CoordinatesXY;
import concurrency.generator.frontend.flowchart.util.MatrixCoordinates;
import concurrency.generator.frontend.flowchart.util.TransportHelper;
import concurrency.generator.frontend.validator.FlowValidator;

public class GeneratorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8746152073589483362L;

	private int windowWidth;
	private int windowHeight;

	private Panel panel;
	private JComboBox<String> connectorDropdown;
	private JComboBox<String> technologyDropdown;
	private JComboBox<String> algorithmDropdown;
	private JButton clearAllButton; 
	private JButton clearButton;
	private JButton startButton;
	private JButton fileChooserButton;
	private JFileChooser fileChooser;
	private JLabel choosenDirectoryLabel;
	private Connector connectorFlowchart;
	private Flowchart selectedMatrixFlowchart;
	private List<Flowchart> flowchartMatrix;
	private List<Flowchart> flowcharts;
	private MouseListener mouseListener;

	public GeneratorWindow() {
		setWindowDimensions();
		this.setBounds(windowWidth, windowHeight, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Java Concurrency Code Generator");
		addGUIComponents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		FlowchartAlgorithmStorage.init();
	}

	private void addGUIComponents() {
		panel = new Panel();
		panel.setLayout(null);

		// create and add flowchart matrix
		flowchartMatrix = GUICreator.createFlowchartMatrix();
		flowchartMatrix.forEach(flowchart -> panel.add(flowchart));
		
		// create and add flowcharts
		flowcharts = GUICreator.createFlowcharts();
		flowcharts.forEach(flowchart -> panel.add(flowchart));
		connectorFlowchart = (Connector) flowcharts.stream().filter(flowchart -> (flowchart instanceof Connector)).findFirst().orElse(null);
		
		// create and add connector components
		panel.add(GUICreator.createConnectorLabel());
		connectorDropdown = GUICreator.createConnectorDropdown();
		panel.add(connectorDropdown);
		
		// create and add technology components
		panel.add(GUICreator.createTechnologyLabel());
		technologyDropdown = GUICreator.createTechnologyDropdown();
		panel.add(technologyDropdown);
		
		// create and add algorithm components
		panel.add(GUICreator.createAlgorithmLabel());
		algorithmDropdown = GUICreator.createAlgorithmDropDown();
		panel.add(algorithmDropdown);
		
		// create and add functionality components
		clearAllButton = GUICreator.createClearAllButton();
		panel.add(clearAllButton);
		clearButton = GUICreator.createClearButton();
		panel.add(clearButton);
		startButton = GUICreator.createStartButton();
		panel.add(startButton);
		
		// create and add file chooser components
		fileChooserButton = GUICreator.createFileChooserButton();
		panel.add(fileChooserButton);
		fileChooser = GUICreator.createFileChooser();
		choosenDirectoryLabel = GUICreator.createChoosenDirectoryLabel();
		panel.add(choosenDirectoryLabel);
		
		// create and add listeners
		setMouseListener();
		addMouseListeners();
		addConnectorDropdownListener();
		addAlgorithmDropdownListener();
		addClearAllButtonListener();
		addClearButtonListener();
		addStartButtonListerner();
		addFileChooserButtonListener();
		
		this.add(panel);
	}

	private void setWindowDimensions() {
		windowWidth = (Toolkit.getDefaultToolkit().getScreenSize().width - WINDOW_WIDTH) / 2;
		windowHeight = (Toolkit.getDefaultToolkit().getScreenSize().height - WINDOW_HEIGHT) / 2;
	}

	private void setMouseListener() {

		mouseListener = new MouseListener() {

			public void mouseClicked(MouseEvent event) {
				Flowchart clickedFlowchart = (Flowchart) event.getSource();
				
				if(flowcharts.contains(clickedFlowchart)) {
					flowcharts.forEach(flowchart -> flowchart.setBorder(BORDER_BLACK));
					TransportHelper.setTarget(clickedFlowchart);
				} else if(TransportHelper.getTarget() != null) {
					TransportHelper.setDestination(clickedFlowchart);
					replaceFlowchart(clickedFlowchart);
				}
				
				if(flowchartMatrix.contains(clickedFlowchart) && clickedFlowchart.isNotEmpty()) {
					selectedMatrixFlowchart = clickedFlowchart;
				}
			}

			public void mousePressed(MouseEvent event) {
			}

			public void mouseReleased(MouseEvent event) {
			}

			public void mouseEntered(MouseEvent event) {
				Flowchart enteredFlowchart = (Flowchart) event.getSource();
				
				if(!flowcharts.contains(enteredFlowchart)) {
					enteredFlowchart.setBorder(BORDER_GREEN);
				}
			}

			public void mouseExited(MouseEvent event) {
				Flowchart exitedFlowchart = (Flowchart) event.getSource();
				
				if(!flowcharts.contains(exitedFlowchart)) {
					exitedFlowchart.setBorder(BORDER_BLACK);
				}
			}

		};
	}
	
	private void addConnectorDropdownListener() {
		connectorDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				String selectedDirectory = ((JComboBox<String>) event.getSource()).getSelectedItem().toString();
				ConnectorEnum connectorEnum = ConnectorEnum.getByDirectory(selectedDirectory);
				
				connectorFlowchart.setConnectorType(connectorEnum);
				connectorFlowchart.setImage(connectorEnum.getPath());	
			}
		});
	}
	
	private void replaceFlowchart(Flowchart source) {
		Flowchart flowchartToReplace = TransportHelper.getDestination();
		
		if(flowchartToReplace == null) {
			return;
		}
		
		CoordinatesXY coordinatesXY = flowchartToReplace.getCoordinatesXY();
		MatrixCoordinates matrixCoordinates = flowchartToReplace.getCoordinates();
		ConnectorEnum connectorType = ConnectorEnum.getByDirectory(connectorDropdown.getSelectedItem().toString());
		
		panel.remove(flowchartToReplace);
		
		FlowchartEnum sourceType = TransportHelper.getTarget().getType();
		Flowchart replacement = new FlowchartBuilder().coordinatesXY(coordinatesXY).matrixCoordinates(matrixCoordinates).flowchartType(sourceType).connectorType(connectorType).build();
		
		if(!sourceType.equals(CONNECTOR)) {
			replacement.setBackground(Color.YELLOW);
			replacement.setOpaque(true);
		}
		
		int index = flowchartMatrix.indexOf(flowchartToReplace);
		flowchartMatrix.set(index, replacement);
		TransportHelper.clear();
		
		replacement.addMouseListener(mouseListener);
		panel.add(replacement);
		panel.revalidate();
		panel.repaint();
	}
	
	private void clearFlowchartMatrix() {
		List<Flowchart> notEmptyFlowcharts = flowchartMatrix.stream().filter(flowchart -> flowchart.isNotEmpty()).collect(Collectors.toList());
		notEmptyFlowcharts.forEach(flowchart -> replaceWithEmpty(flowchart));
	}

	private void addMouseListeners() {
		flowchartMatrix.forEach(flowchart -> flowchart.addMouseListener(mouseListener));
		flowcharts.forEach(flowchart -> flowchart.addMouseListener(mouseListener));
	}
	
	private void addClearAllButtonListener() {
		clearAllButton.addActionListener((ActionEvent e) -> {
			clearFlowchartMatrix();
			panel.revalidate();
			panel.repaint();
		});
	}
	
	private void addClearButtonListener() {
		clearButton.addActionListener((ActionEvent e) -> {
			if(selectedMatrixFlowchart != null && selectedMatrixFlowchart.isNotEmpty()) {
				replaceWithEmpty(selectedMatrixFlowchart);
				panel.revalidate();
				panel.repaint();
			}
		});
	}
	
	private void addStartButtonListerner() {
		startButton.addActionListener((ActionEvent e) -> {
			FlowValidator flowValidator = new FlowValidator(flowchartMatrix);
			flowValidator.validate();
			if(flowValidator.hasErrors()) {
				GUIUtilities.showErrors(flowValidator.getErrorsAsOneString());
				return;
			}
			
			String selectedJavaTechnology = technologyDropdown.getSelectedItem().toString();
			String selectedAlgorithm = algorithmDropdown.getSelectedItem().toString();
			
			List<CodeElement> codeElements = new CodeConverter(flowchartMatrix).convertToCodeElements();
			
			String targetDirectory = fileChooser.getSelectedFile().getPath();
			
			try {
				SourceCodeGeneratorController.generateSourceCode(codeElements, selectedJavaTechnology, selectedAlgorithm, targetDirectory);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void addAlgorithmDropdownListener() {
		algorithmDropdown.addActionListener((ActionEvent e) -> {
			@SuppressWarnings("unchecked")
			String selectedAlgorithm = ((JComboBox<String>) e.getSource()).getSelectedItem().toString();
			List<Flowchart> selectedAgorithmFlowcharts = FlowchartAlgorithmStorage.getAlgorithm(selectedAlgorithm);
				
			clearFlowchartMatrix();
				
			for(Flowchart flowchart : selectedAgorithmFlowcharts) {
				Flowchart matrixFlowchart = flowchartMatrix.stream().filter(flow -> flow.getCoordinates().equals(flowchart.getCoordinates())).findFirst().orElse(null);
				panel.remove(matrixFlowchart);
				int index = flowchartMatrix.indexOf(matrixFlowchart);
				flowchartMatrix.set(index, flowchart);
				flowchart.setBackground(Color.YELLOW);
				flowchart.setOpaque(true);
				flowchart.addMouseListener(mouseListener);
				panel.add(flowchart);
			}
				
			panel.revalidate();
			panel.repaint();
		});
	}
	
	private void addFileChooserButtonListener() {
		fileChooserButton.addActionListener((ActionEvent e) -> {
			if(fileChooser.showOpenDialog(fileChooserButton) == JFileChooser.APPROVE_OPTION) {
				String directoryPath = fileChooser.getSelectedFile().getAbsolutePath();
				choosenDirectoryLabel.setText("Selected directory: " + directoryPath);
			}
		});
	}
	
	private void replaceWithEmpty(Flowchart flowchart) {
		int index = flowchartMatrix.indexOf(flowchart);
		panel.remove(flowchart);
		Flowchart empty = new FlowchartBuilder().coordinatesXY(flowchart.getCoordinatesXY()).matrixCoordinates(flowchart.getCoordinates()).flowchartType(EMPTY).build();
		empty.addMouseListener(mouseListener);
		panel.add(empty);
		flowchartMatrix.set(index, empty);
	}
}
