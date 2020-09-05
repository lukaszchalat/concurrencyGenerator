package concurrency.generator.frontend.flowchart.util;

import concurrency.generator.frontend.flowcharts.Flowchart;

public class NeighbourFlowcharts {
	
	Flowchart centralFlowchart;
	Flowchart topNeighbour;
	Flowchart rightNeightbour;
	Flowchart bottomNeighbour;
	Flowchart leftNeighbour;
	
	public NeighbourFlowcharts(Flowchart centralFlowchart, Flowchart topNeighbour, Flowchart rightNeightbour, Flowchart bottomNeighbour, Flowchart leftNeighbour) {
		this.topNeighbour = topNeighbour;
		this.rightNeightbour = rightNeightbour;
		this.bottomNeighbour = bottomNeighbour;
		this.leftNeighbour = leftNeighbour;
	}

	public Flowchart getTopNeighbour() {
		return topNeighbour;
	}

	public Flowchart getRightNeightbour() {
		return rightNeightbour;
	}

	public Flowchart getBottomNeighbour() {
		return bottomNeighbour;
	}

	public Flowchart getLeftNeighbour() {
		return leftNeighbour;
	}
	
	public boolean isEmpty() {
		return topNeighbour == null && rightNeightbour == null && bottomNeighbour == null && leftNeighbour == null;
	}
}
