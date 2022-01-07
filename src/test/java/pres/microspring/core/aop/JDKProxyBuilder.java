package pres.microspring.core.aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * micro-spring
 * 2022/1/7 17:12
 *
 * @author wangsicheng
 * @since
 **/
public class JDKProxyBuilder implements InvocationHandler {

    Object originalObj;


    public static Object creatProxyObject(Object originalObj) {
        JDKProxyBuilder jdkProxyBuilder = new JDKProxyBuilder();
        jdkProxyBuilder.originalObj = originalObj;
        return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), jdkProxyBuilder);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(method);
        Object result = method.invoke(originalObj, args);
        if (result != null) {
            result(result);
        }
        after(method);
        return result;
    }

    private void before(Method method) {
        System.out.println(method.getName()+"方法执行before(JDK)");
    }

    private void after(Method method) {
        System.out.println(method.getName()+"方法执行after(JDK)");
    }

    private void result(Object o) {
        System.out.println(o.toString());
    }


}

