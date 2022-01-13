package pres.microspring.core.aop.methodInterceptor;


import pres.microspring.core.ioc.Behavior;

/**
 * micro-spring
 * 2021/12/31 14:30
 *
 * @author wangsicheng
 * @since
 **/
public class HelloWorldService implements Behavior {

    HelloWorld helloWorld;

    public HelloWorldService setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
        return this;
    }

    @Override
    public void say() {
        System.out.println(helloWorld.getName() + " say : Hello World!");
    }
}
