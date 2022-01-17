package pres.microspring.core.aop.support;




import java.io.Serializable;

/**
 * 表达式切入点的抽象超类，
 * 提供位置和表达式属性。
 */
public abstract class AbstractExpressionPointcut implements ExpressionPointcut, Serializable {


	private String expression;


	public void setExpression(String expression) {
		this.expression = expression;
		onSetExpression(expression);
	}

	/**
	 * 当一个新的切入点表达式被设置时调用。
	 * 如果可能，此时应该解析表达式。
	 */
	protected void onSetExpression(String expression) throws IllegalArgumentException {
	}

	/**
	 * 返回这个切入点的表达式。
	 */
	@Override
	public String getExpression() {
		return this.expression;
	}

}
