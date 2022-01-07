package pres.microspring.core.ioc;


import pres.microspring.core.dom.Dom4jXmlResolver;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.DefinitionBeanFactory;

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
        BeanFactory beanFactory = new DefinitionBeanFactory();
        for (BeanDefinition beanDefinition:list
             ) {
            beanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
        }
        HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        helloWorld.say();
    }
}
