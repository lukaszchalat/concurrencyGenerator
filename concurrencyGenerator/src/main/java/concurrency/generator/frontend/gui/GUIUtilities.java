package concurrency.generator.frontend.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import concurrency.generator.frontend.flowcharts.Flowchart;

public class GUIUtilities {
	
	public static List<Flowchart> convertToList(Flowchart flowchartMatrix[][]) {
		List<Flowchart> list = new ArrayList<Flowchart>();
		
		for(Flowchart[] flowcharts : flowchartMatrix) {
			list.addAll(Arrays.asList(flowcharts));
		}
		
		return list;
	}
	
	public static void showErrors(String errors) {
		JOptionPane optionPane = new JOptionPane(errors, JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog("Schemat posiada nastêpuj¹ce b³êdy");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

}
