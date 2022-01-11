/*
 * Copyright 2002-2020 the original author or authors.
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


import pres.microspring.core.aopalliance.intercept.MethodInterceptor;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;


final class JdkDynamicAopProxy implements AopProxy, InvocationHandler, Serializable {

    private int depth = 0;

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        List<MethodSuppory> methodSupporyList = this.advisedSupport.getMethodSupporyList();
        if(methodSupporyList == null || methodSupporyList.size()<=0){
            return this.advisedSupport.getTargetSource().getTarget();
        }
        return init(0,this.advisedSupport.getTargetSource().getTarget());
    }
    private Object init(int i,Object targetObject){
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        jdkDynamicAopProxy.depth = i;
        targetObject = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), jdkDynamicAopProxy);
        i++;
        if(i>=this.advisedSupport.getMethodSupporyList().size()){
            return targetObject;
        }
        return init(i,targetObject);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodSuppory methodSuppory = this.advisedSupport.getMethodSupporyList().get(depth);
        MethodMatcher methodMatcher = methodSuppory.getMethodMatcher();
        MethodInterceptor methodInterceptor = methodSuppory.getMethodInterceptor();
        if (methodMatcher.matches(method, advisedSupport.getTargetSource().getTargetClass())) {
            return methodInterceptor.invoke(new DefaultMethodInvocation(this.advisedSupport.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(this.advisedSupport.getTargetSource().getTarget(),args);
    }
}
