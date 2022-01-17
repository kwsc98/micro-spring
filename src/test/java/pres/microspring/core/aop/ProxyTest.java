package pres.microspring.core.aop;


import pres.microspring.core.model.Behavior;
import pres.microspring.core.model.HelloWorld;
import pres.microspring.core.model.HelloWorldService;


/**
 * micro-spring
 * 2022/1/7 17:12
 *
 * @author wangsicheng
 * @since
 **/
public class ProxyTest {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/micro-spring.xml");
//        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
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
        Behavior helloWorldProxyObject = (Behavior) JDKProxyBuilder.creatProxyObject(helloWorld1);
        helloWorld1 = (HelloWorld) helloWorldProxyObject;
        helloWorld1.say();

    }
}
