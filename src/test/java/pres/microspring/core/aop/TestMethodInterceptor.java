package pres.microspring.core.aop;


import pres.microspring.core.aopalliance.intercept.MethodInterceptor;
import pres.microspring.core.aopalliance.intercept.MethodInvocation;

import java.util.Date;

/**
 * micro-spring
 * 2022/1/13 10:38
 *
 * @author wangsicheng
 * @since
 **/
public class TestMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("run time:"+new Date().toString());
        Object object = invocation.proceed();
        System.out.println("end time:"+new Date().toString());
        return object;
    }
}
