/*
 * Copyright 2002-2018 the original author or authors.
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

package pres.microspring.core.aop;


/**
 * 代理目标类
 */
public class TargetSource {
	/**
	 * 代理目标类的类型
	 */
    private final Class<?> targetClass;
	/**
	 * 代理目标类的实例化对象
	 */
	private final Object targetObject;

	public TargetSource(Object target){
		this.targetClass = target.getClass();
		this.targetObject = target;
	};

	public Class<?> getTargetClass(){
		return this.targetClass;
	}


	public Object getTarget(){
		return this.targetObject;
	}


}
