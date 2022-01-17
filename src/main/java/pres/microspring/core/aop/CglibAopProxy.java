package pres.microspring.core.aop;


import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * micro-spring
 * 2022/1/11 16:18
 * 基于Cglib的代理方式
 * @since
 **/
final public class CglibAopProxy implements AopProxy, Serializable {
    /**
     * SpringAop代理支持类
     **/
    private final AdvisedSupport advisedSupport;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * 获取目标类的代理类
     */
    @Override
    public Object getProxy() {
        System.out.println("使用cglib代理目标："+advisedSupport.getTargetSource().getTarget());
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.advisedSupport.getTargetSource().getTargetClass());
        //setCallbacks,支持多重代理
        enhancer.setCallbacks(getCallback());
        return enhancer.create();
    }

    /**
     * 获取多重代理Callback[]
     */
    public Callback[] getCallback() {
        Callback[] callbacks = new Callback[this.advisedSupport.getMethodSupporyList().size()];
        //遍历MethodSupporyList
        for(int i =0;i<this.advisedSupport.getMethodSupporyList().size();i++){
            callbacks[i] = new SpringCglibProxyInterceptor(this.advisedSupport.getMethodSupporyList().get(i));
        }
        return callbacks;
    }

    private class SpringCglibProxyInterceptor implements MethodInterceptor {
        //在这个拦截器里放一个过滤器链 SpringCglibProxy
        MethodSuppory methodSuppory;

        public SpringCglibProxyInterceptor(MethodSuppory methodSuppory) {
            this.methodSuppory = methodSuppory;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            pres.microspring.core.aopalliance.intercept.MethodInterceptor methodInterceptor = methodSuppory.getMethodInterceptor();
            MethodMatcher methodMatcher = methodSuppory.getMethodMatcher();
            if (methodMatcher.matches(method, advisedSupport.getTargetSource().getTargetClass())) {
                return methodInterceptor.invoke(new DefaultMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects));
            }
            return methodProxy.invoke(o, objects);
        }
    }
}
