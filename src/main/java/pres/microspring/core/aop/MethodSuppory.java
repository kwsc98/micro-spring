package pres.microspring.core.aop;


import pres.microspring.core.aop.MethodMatcher;
import pres.microspring.core.aopalliance.intercept.MethodInterceptor;

/**
 * micro-spring
 * 2022/1/11 17:20
 *
 * @author wangsicheng
 * @since
 **/
public class MethodSuppory {

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
