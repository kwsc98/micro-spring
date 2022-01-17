package pres.microspring.core.aop.aspectj;



import pres.microspring.core.aop.Pointcut;
import pres.microspring.core.aop.support.AbstractGenericPointcutAdvisor;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.BeanFactoryAware;

/**
 * SpringAop织入配置类
 */
public class AspectJExpressionPointcutAdvisor extends AbstractGenericPointcutAdvisor implements BeanFactoryAware {

	/**
	 * Aspect匹配器
	 */
	private final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

	/**
	 * 设置匹配表达式
	 */
	public void setExpression(String expression) {
		this.pointcut.setExpression(expression);
	}

	/**
	 * 获取匹配表达式
	 */
	public String getExpression() {
		return this.pointcut.getExpression();
	}


	/**
	 * 获取切入点（Aspect匹配器）
	 */
	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.pointcut.setBeanFactory(beanFactory);
	}
}
