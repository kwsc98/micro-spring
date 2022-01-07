package pres.microspring.core.aop;


import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * micro-spring
 * 2022/1/7 19:04
 *
 * @author wangsicheng
 * @since
 **/
public abstract AdapterInvocationHandler implements MethodInterceptor, InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }

}
