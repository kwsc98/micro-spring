package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/29 18:45
 *
 * @author wangsicheng
 * @since
 **/
public class HelloWorld {

    private String name;

    private HelloWorldService helloWorldService;

    public void say() {
        helloWorldService.say();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
