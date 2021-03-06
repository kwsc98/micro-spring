/*
 * Copyright 2002-2021 the original author or authors.
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



import pres.microspring.core.aopalliance.AopConfig;

import java.io.Serializable;


/**
 * 默认的springAopFactory类
 * @author kwsc98
 */
public class DefaultAopProxyFactory implements AopProxyFactory, Serializable {

	/**
	 * 根据配置获取对应代理方式
	 * @author kwsc98
	 */
	@Override
	public AopProxy createAopProxy(AdvisedSupport config)  {
         if(AopConfig.IsCglibAop){
			 return new CglibAopProxy(config);
		 }else {
			 return new JdkDynamicAopProxy(config);
		 }
	}

}
