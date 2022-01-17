package pres.microspring.core.aop;


import pres.microspring.core.aop.aspectj.AspectJExpressionPointcut;
import pres.microspring.core.model.HelloWorldService;

import java.lang.reflect.Method;

/**
 * micro-spring
 * 2022/1/10 18:58
 *
 * @author wangsicheng
 * @since
 **/
public class AspectjTest {
    public static void main(String[] args) throws NoSuchMethodException {
        AspectJExpressionPointcut aspectJExpressionPointcut =  new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* pres.microspring.core.ioc.*.*(..))");
        System.out.println(aspectJExpressionPointcut.matches(HelloWorldService.class));
        Method method = HelloWorldService.class.getDeclaredMethod("say");
        System.out.println(aspectJExpressionPointcut.matches(method,null));
    }
}
