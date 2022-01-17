package pres.microspring.core.context;


import pres.microspring.core.model.HelloWorld;

/**
 * micro-spring
 * 2022/1/7 16:40
 *
 * @author wangsicheng
 * @since
 **/
public class ContextTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        helloWorld.say();
    }
}
