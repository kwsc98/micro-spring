package pres.microspring.core.ioc;


import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.DefinitionBeanFactory;

/**
 * micro-spring
 * 2021/12/29 18:41
 *
 * @author wangsicheng
 * @since
 **/
public class IocTest {
    public static void main(String[] args) {

        DefinitionBeanFactory beanFactory = new DefinitionBeanFactory();

        String id = "helloWorld";
        String className = "pres.microspring.core.ioc.HelloWorld";
        String id2 = "helloWorldService";
        String className2 = "pres.microspring.core.ioc.HelloWorldService";

        //初始化HelloWorld
        BeanDefinition beanDefinition = new BeanDefinition(id, className);
        beanDefinition.getPropertyValues().add(PropertyValue.init().setName("name").setValue("kwsc98"));
        beanDefinition.getPropertyValues().add(PropertyValue.init().setName("helloWorldService").setRef(id2));

        //初始化HelloWorldService
        BeanDefinition beanDefinition2 = new BeanDefinition(id2, className2);
        beanDefinition2.getPropertyValues().add(PropertyValue.init().setName("helloWorld").setRef(id));


        beanFactory.registerBeanDefinition(id, beanDefinition);
        beanFactory.registerBeanDefinition(id2, beanDefinition2);

        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean(id);
        helloWorld.say();

    }
}
