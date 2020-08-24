package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.FOR_LOOP_ELEMENT;

public class ForLoopElement extends CodeElement {
	
	private String iteratorName;
	private String iteratorStartValue;
	private String conditionSign;
	private String conditionValue;
	private String iteratorChangeSign;
	private String iteratorChangeValue;
	
	public ForLoopElement() {
		super(FOR_LOOP_ELEMENT);
	}

	public String getIteratorName() {
		return iteratorName;
	}

	public void setIteratorName(String iteratorName) {
		this.iteratorName = iteratorName;
	}

	public String getIteratorStartValue() {
		return iteratorStartValue;
	}

	public void setIteratorStartValue(String iteratorStartValue) {
		this.iteratorStartValue = iteratorStartValue;
	}

	public String getConditionSign() {
		return conditionSign;
	}

	public void setConditionSign(String conditionSign) {
		this.conditionSign = conditionSign;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public String getIteratorChangeSign() {
		return iteratorChangeSign;
	}

	public void setIteratorChangeSign(String iteratorChangeSign) {
		this.iteratorChangeSign = iteratorChangeSign;
	}

	public String getIteratorChangeValue() {
		return iteratorChangeValue;
	}

	public void setIteratorChangeValue(String iteratorChangeValue) {
		this.iteratorChangeValue = iteratorChangeValue;
	}
}
