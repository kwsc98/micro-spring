package pres.microspring.core.aop;


import pres.microspring.core.aop.aspectj.AspectJExpressionPointcut;
import pres.microspring.core.aop.aspectj.AspectJExpressionPointcutAdvisor;
import pres.microspring.core.context.ApplicationContext;
import pres.microspring.core.context.ClassPathXmlApplicationContext;
import pres.microspring.core.ioc.HelloWorld;

import java.lang.reflect.Method;

/**
 * micro-spring
 * 2022/1/13 10:44
 *
 * @author wangsicheng
 * @since
 **/
public class AopProxyTest {
    public static void main(String[] args) throws NoSuchMethodException {
        String expression = "execution(* pres.microspring.core.aop.methodInterceptor.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        Method method = HelloWorld.class.getDeclaredMethod("say");
        System.out.println(aspectJExpressionPointcut.getMethodMatcher().matches(method,HelloWorld.class));




        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = (AspectJExpressionPointcutAdvisor) applicationContext.getBean("aspectJExpressionPointcutAdvisor");
        Method method1 = HelloWorld.class.getDeclaredMethod("say");
        System.out.println(aspectJExpressionPointcutAdvisor.getPointcut().getMethodMatcher().matches(method,HelloWorld.class));

        helloWorld.say();
    }
}
