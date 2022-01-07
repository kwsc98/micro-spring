package pres.microspring.core.aop;


import pres.microspring.core.context.ApplicationContext;
import pres.microspring.core.context.ClassPathXmlApplicationContext;
import pres.microspring.core.ioc.Behavior;
import pres.microspring.core.ioc.HelloWorld;

/**
 * micro-spring
 * 2022/1/7 17:12
 *
 * @author wangsicheng
 * @since
 **/
public class ProxyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        //该系统参数可以在根目录下查看JDK生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //通过JDK的方式生成代理类
        Behavior behavior = (Behavior) JDKProxyBuilder.creatProxyObject(helloWorld);
        helloWorld.setName("JdkKwsc98");
        behavior.say();
        //通过Cglib的方式生成代理类
        HelloWorld helloWorldProxyObject = (HelloWorld) CglibProxyBuilder.creatProxyObject(helloWorld);
        helloWorld.setName("CglibKwsc98");
        helloWorldProxyObject.say();
    }
}
