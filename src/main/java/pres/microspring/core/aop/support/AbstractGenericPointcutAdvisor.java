package pres.microspring.core.aop.support;


import pres.microspring.core.aop.PointcutAdvisor;
import pres.microspring.core.aopalliance.aop.Advice;

import java.io.Serializable;

/**
 * 设置代理方法的抽象类
 */
public abstract class AbstractGenericPointcutAdvisor implements PointcutAdvisor, Serializable {

	private Advice advice = EMPTY_ADVICE;


	/**
	 * 设置代理方法
	 */
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}


}
