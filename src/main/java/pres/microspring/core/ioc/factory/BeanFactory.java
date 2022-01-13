package pres.microspring.core.ioc.factory;


import pres.microspring.core.ioc.BeanDefinition;

import java.util.List;

/**
 * @author kwsc98
 */
public interface BeanFactory {
    /**
     * name : bean的id
     * return bean的实例对象
     */
    Object getBean(String beanName);

    /**
     * beanClass : bean的id
     * return bean的实例对象列表
     */
    List<Object> getBeanByBusinessType(Class<?> beanClass);


}
