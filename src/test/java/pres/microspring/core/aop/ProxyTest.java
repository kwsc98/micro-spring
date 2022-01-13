package pres.microspring.core.aop;


import pres.microspring.core.context.ApplicationContext;
import pres.microspring.core.context.ClassPathXmlApplicationContext;
import pres.microspring.core.ioc.Behavior;
import pres.microspring.core.ioc.HelloWorld;
import pres.microspring.core.ioc.HelloWorldService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
//        Behavior behavior = (Behavior) JDKProxyBuilder.creatProxyObject(helloWorld);
//        helloWorld.setName("JdkKwsc98");
//        behavior.say();
        //通过Cglib的方式生成代理类
        HelloWorld helloWorld1 = new HelloWorld();
        helloWorld1.setName("CglibKwsc98");
        helloWorld1.setHelloWorldService(new HelloWorldService().setHelloWorld(helloWorld1));
        HelloWorld helloWorldProxyObject = (HelloWorld) CglibProxyBuilder.creatProxyObject(helloWorld1);
        helloWorldProxyObject.say();

    }
}
