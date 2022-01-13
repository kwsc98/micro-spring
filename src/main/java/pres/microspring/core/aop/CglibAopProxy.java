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
 *
 * @author wangsicheng
 * @since
 **/
final public class CglibAopProxy implements AopProxy, Serializable {

    private final AdvisedSupport advisedSupport;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * Create a new proxy object.
     */
    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallbacks(getCallback());
        return enhancer.create();
    }


    public Callback[] getCallback() {
        Callback[] callbacks = new Callback[this.advisedSupport.getMethodSupporyList().size()];
        for(int i =0;i<this.advisedSupport.getMethodSupporyList().size();i++){
            callbacks[i] = new SpringCglibProxyInterceptor(this.advisedSupport.getMethodSupporyList().get(i));
        }
        return callbacks;
    }

    private class SpringCglibProxyInterceptor implements MethodInterceptor {//在这个拦截器里放一个过滤器链 SpringCglibProxy
        MethodSuppory methodSuppory;

        public SpringCglibProxyInterceptor(MethodSuppory methodSuppory) {
            System.out.println("SpringCglibProxyInterceptor的构造方法");
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
