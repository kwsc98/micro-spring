package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/31 14:30
 *
 * @author wangsicheng
 * @since
 **/
public class HelloWorldService implements Behavior {

    HelloWorld helloWorld;

    @Override
    public void say() {
        System.out.println(helloWorld.getName() + " say : Hello World!");
    }
}
