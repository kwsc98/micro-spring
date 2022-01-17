package pres.microspring.core.dom;


import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.factory.DefinitionBeanFactory;
import pres.microspring.core.model.HelloWorld;

import java.util.List;

/**
 * micro-spring
 * 2022/1/7 15:37
 *
 * @author wangsicheng
 * @since
 **/
public class DomTest {
    public static void main(String[] args) {
        List<BeanDefinition> list = Dom4jXmlResolver.resolverXml("/micro-spring.xml");
        DefinitionBeanFactory beanFactory = new DefinitionBeanFactory();
        for (BeanDefinition beanDefinition:list
             ) {
            beanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
        }
        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.say();

    }
}
