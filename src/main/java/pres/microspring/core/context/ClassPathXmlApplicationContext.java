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
        //step2 通过Dom4jXmlResolver读取xml,获取bean元数据
        List<BeanDefinition> list = Dom4jXmlResolver.resolverXml(path);
        for (BeanDefinition beanDefinition : list
             ) {
            //step3 将解析后的BeanDefinition列表放入beanFactory
            beanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
        }
        //step4 再初始化普通bean之前先初始化BeanPostProcessor（用于再初始化bean前后做一个特殊处理，比如说aop代理等等）
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
        //step4-1 根据BeanPostProcessor.class获取对应的beanObject
        List<Object> beanPostProcessors = beanFactory.getBeanByBusinessType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            //step4-2 将BeanPostProcessor加入beanFactory
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }


}
