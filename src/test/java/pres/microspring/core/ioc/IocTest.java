package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/29 18:41
 *
 * @author wangsicheng
 * @since
 **/
public class IocTest {
    public static void main(String[] args) throws ClassNotFoundException {
            BeanFactory beanFactory = new BeanFactory();

            String id = "helloworld";
            String className = "pres.microspring.core.ioc.HelloWorld";
            Object o = Class.forName(className);
            BeanDefinition beanDefinition = new BeanDefinition(o);
            beanFactory.registerBeanDefinition(id, beanDefinition);

            HelloWorld helloWorld = (HelloWorld) beanFactory.getBean(id);
            helloWorld.say();
    }
}
