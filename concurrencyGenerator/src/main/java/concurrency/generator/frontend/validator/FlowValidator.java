package concurrency.generator.frontend.validator;

import static concurrency.generator.frontend.validator.ValidatorErrors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import concurrency.generator.frontend.flowchart.Flowchart;

public class FlowValidator {

	private List<Flowchart> flowcharts;

	private List<String> errors = new ArrayList<>();

	private ValidationHelper helper;

	public FlowValidator(List<Flowchart> flowcharts) {
		this.flowcharts = flowcharts.stream().filter(flowchart -> flowchart.isNotEmpty()).collect(Collectors.toList());
		this.helper = new ValidationHelper(this.flowcharts);
	}

	public void validate() {
		if (flowcharts.isEmpty()) {
			errors.add(HAS_NO_ELEMENTS);
			return;
		}

		if (!helper.hasStartElement())
			errors.add(HAS_NO_START);
		if (!helper.hasStopElement())
			errors.add(HAS_NO_STOP);

	}

	public List<String> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public String getErrorsAsOneString() {
		StringBuilder errorString = new StringBuilder();

		for (String error : errors) {
			errorString.append(error);
			errorString.append("\n");
		}

		return errorString.toString();
	}
}
