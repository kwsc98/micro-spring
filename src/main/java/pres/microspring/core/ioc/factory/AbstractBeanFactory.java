package pres.microspring.core.ioc.factory;

import pres.microspring.core.ioc.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author kwsc98
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        Object beanObject = beanDefinition.getBean();
        if (beanObject == null) {
            beanObject = initBean(beanDefinition);
        }
        return beanObject;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 实例化bean
     */
    protected abstract Object initBean(BeanDefinition beanDefinition);

}
