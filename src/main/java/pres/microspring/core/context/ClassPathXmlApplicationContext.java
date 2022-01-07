package pres.microspring.core.context;


import pres.microspring.core.dom.Dom4jXmlResolver;
import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.DefinitionBeanFactory;

import java.util.List;

/**
 * micro-spring
 * 2022/1/7 16:30
 *
 * @author wangsicheng
 * @since
 **/
public class ClassPathXmlApplicationContext implements ApplicationContext{
    private final BeanFactory beanFactory;
    private final String path;

    public ClassPathXmlApplicationContext(String path){
        this.path = path;
        beanFactory = new DefinitionBeanFactory();
        init();
    }
    private void init(){
        List<BeanDefinition> list = Dom4jXmlResolver.resolverXml(path);
        for (BeanDefinition beanDefinition : list
             ) {
            beanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
        }
    }

    /**
     * name : bean的id
     * return bean的实例对象
     *
     * @param name
     */
    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

}
