package pres.microspring.core.aop.support;


import com.sun.istack.internal.Nullable;
import pres.microspring.core.aop.Pointcut;

/**
 * Aspect支持类
 */
public interface ExpressionPointcut extends Pointcut {

	/**
	 * return 返回Pointcut切入点表达式
	 */
	@Nullable
	String getExpression();

}
