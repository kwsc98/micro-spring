package pres.microspring.core.aop;


import pres.microspring.core.aop.aspectj.AspectJExpressionPointcut;
import pres.microspring.core.ioc.HelloWorld;

/**
 * micro-spring
 * 2022/1/10 18:58
 *
 * @author wangsicheng
 * @since
 **/
public class AspectjTest {
    public static void main(String[] args) {
        AspectJExpressionPointcut aspectJExpressionPointcut =  new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* pres.microspring.core.*.*(..))");
        System.out.println(aspectJExpressionPointcut.matches(CglibProxyBuilder.class));
    }
}
