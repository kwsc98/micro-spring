package pres.microspring.core.aop;


import pres.microspring.core.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * micro-spring
 * 2022/1/11 17:39
 * springAop代理接口的入参
 * @author wangsicheng
 * @since
 **/
public class DefaultMethodInvocation implements MethodInvocation {

    protected Object target;

    protected Method method;

    protected Object[] arguments;

    public DefaultMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
