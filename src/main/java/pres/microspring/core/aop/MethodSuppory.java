package pres.microspring.core.aop;


import pres.microspring.core.aopalliance.intercept.MethodInterceptor;

/**
 * aop类过滤处理支持类
 **/
public class MethodSuppory {
    /**
     * springAop代理方法
     **/
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器
     **/
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
