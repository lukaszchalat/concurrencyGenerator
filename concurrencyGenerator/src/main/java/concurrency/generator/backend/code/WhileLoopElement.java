package concurrency.generator.backend.code;

import static concurrency.generator.backend.enums.CodeElementEnum.WHILE_LOOP_ELEMENT;

public class WhileLoopElement extends CodeElement {
	
	private String leftConditionVariable;
	private String rightConditionVariable;
	private String conditionSign;

	public WhileLoopElement() {
		super(WHILE_LOOP_ELEMENT);
	}

	public String getLeftConditionVariable() {
		return leftConditionVariable;
	}

	public void setLeftConditionVariable(String leftConditionVariable) {
		this.leftConditionVariable = leftConditionVariable;
	}

	public String getRightConditionVariable() {
		return rightConditionVariable;
	}

	public void setRightConditionVariable(String rightConditionVariable) {
		this.rightConditionVariable = rightConditionVariable;
	}

	public String getConditionSign() {
		return conditionSign;
	}

	public void setConditionSign(String conditionSign) {
		this.conditionSign = conditionSign;
	}
	
	public String toString() {
		StringBuilder whileLoop = new StringBuilder();
		
		whileLoop.append("while(");
		whileLoop.append(leftConditionVariable + " ");
		whileLoop.append(conditionSign + " ");
		whileLoop.append(rightConditionVariable + ") {\n");
		whileLoop.append("%loopCode%\n");
		whileLoop.append("}\n");
		
		return whileLoop.toString();
	}
}
