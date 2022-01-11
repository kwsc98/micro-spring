/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
