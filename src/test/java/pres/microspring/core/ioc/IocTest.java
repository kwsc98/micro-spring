package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/29 18:41
 *
 * @author wangsicheng
 * @since
 **/
public class IocTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            BeanFactory beanFactory = new BeanFactory();
            String id = "helloworld";
            String className = "pres.microspring.core.ioc.HelloWorld";
            //调用Class.forName获取类对象，通过newInstance通过无参构造器实例化类对象
            Object o = Class.forName(className).newInstance();
            BeanDefinition beanDefinition = new BeanDefinition(o);
            beanFactory.registerBeanDefinition(id, beanDefinition);
            HelloWorld helloWorld = (HelloWorld) beanFactory.getBean(id);
            helloWorld.say();
    }
}
