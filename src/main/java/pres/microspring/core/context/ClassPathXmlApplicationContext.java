package pres.microspring.core.context;


import pres.microspring.core.aop.BeanPostProcessor;
import pres.microspring.core.dom.Dom4jXmlResolver;
import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.factory.AbstractBeanFactory;
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
    private final AbstractBeanFactory beanFactory;
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
        registerBeanPostProcessors();
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

    /**
     * beanClass : bean的id
     * return bean的实例对象列表
     *
     * @param beanClass
     */
    @Override
    public List<Object> getBeanByBusinessType(Class<?> beanClass) {
        return beanFactory.getBeanByBusinessType(beanClass);
    }

    /**
     * 在实例化普通bean前,先将初始化BeanPostProcessor
     */
    private void registerBeanPostProcessors() {
        List<Object> beanPostProcessors = beanFactory.getBeanByBusinessType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }


}
