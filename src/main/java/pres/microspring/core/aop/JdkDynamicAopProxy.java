package pres.microspring.core.aop;


import pres.microspring.core.aopalliance.intercept.MethodInterceptor;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 基于JDK方式进行目标类的代理
 **/
final class JdkDynamicAopProxy implements AopProxy, InvocationHandler, Serializable {
    /**
     * 目标类的代理深度
     **/
    private int depth = 0;
    /**
     * SpringAop代理支持类
     **/
    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        System.out.println("使用JDK代理目标："+advisedSupport.getTargetSource().getTarget());
        List<MethodSuppory> methodSupporyList = this.advisedSupport.getMethodSupporyList();
        if(methodSupporyList == null || methodSupporyList.size()<=0){
            return this.advisedSupport.getTargetSource().getTarget();
        }
        //这里通过递归的方式进行目标类的多重代理
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
