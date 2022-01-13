package pres.microspring.core.aop.methodInterceptor;


import pres.microspring.core.ioc.Behavior;

/**
 * micro-spring
 * 2021/12/29 18:45
 *
 * @author wangsicheng
 * @since
 **/
public class HelloWorld implements Behavior {

    private String name;

    private HelloWorldService helloWorldService;


    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void say()  {
        try{
            helloWorldService.say();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
