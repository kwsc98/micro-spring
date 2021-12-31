package pres.microspring.core.ioc;



/**
 * @author kwsc98
 */
public interface BeanFactory {
    /**
     * name : bean的id
     * return bean的实例对象
     */
    Object getBean(String name);
    /**
     * 获取bean的实例对象
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}
