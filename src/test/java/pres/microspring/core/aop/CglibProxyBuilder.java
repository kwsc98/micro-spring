package pres.microspring.core.aop;


/**
 * StudyJava
 * 2021/12/23 16:38
 *
 * @author wangsicheng
 * @since
 **/


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 目标对象拦截器，实现MethodInterceptor
 *
 * @author zghw
 */
public class CglibProxyBuilder implements MethodInterceptor {
    private final Object object;

    public static Object creatProxyObject(Object originalObj) {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(originalObj.getClass());
        enhancer.setCallback(new CglibProxyBuilder(originalObj));
        return enhancer.create();
    }

    CglibProxyBuilder(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before(method);
        Object result = methodProxy.invoke(object, objects);
        if (result != null) {
            result(result);
        }
        after(method);
        return result;
    }
    private void before(Method method) {
        System.out.println(method.getName()+"方法执行before(CGLIB)");
    }

    private void after(Method method) {
        System.out.println(method.getName()+"方法执行after(CGLIB)");
    }

    private void result(Object o) {
        System.out.println(o.toString());
    }
}