package pres.microspring.core.aop;



import pres.microspring.core.model.Behavior;
import pres.microspring.core.model.HelloWorld;
import pres.microspring.core.context.ApplicationContext;
import pres.microspring.core.context.ClassPathXmlApplicationContext;


/**
 * micro-spring
 * 2022/1/13 10:44
 *
 * @author wangsicheng
 * @since
 **/
public class AopProxyTest {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
        Behavior helloWorld = (Behavior) applicationContext.getBean("helloWorld");
        helloWorld.say();

    }
}
