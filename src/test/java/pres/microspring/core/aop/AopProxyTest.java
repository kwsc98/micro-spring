package pres.microspring.core.aop;



import pres.microspring.core.model.Behavior;
import pres.microspring.core.context.ApplicationContext;
import pres.microspring.core.context.ClassPathXmlApplicationContext;
import pres.microspring.core.model.HelloWorld;


/**
 * micro-spring
 * 2022/1/13 10:44
 *
 * @author wangsicheng
 * @since
 **/
public class AopProxyTest {
    public static void main(String[] args) {
        //step1 通过ApplicationContext初始化Ioc容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        helloWorld.say();

    }
}
