package pres.microspring.core.ioc.factory;

import pres.microspring.core.aop.BeanPostProcessor;
import pres.microspring.core.ioc.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author kwsc98
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    @Override
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition.getBean() == null) {
            initBean(beanDefinition);
            initBeanPostProcessor(beanDefinition);
        }
        return beanDefinition.getBean();
    }


    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 实例化bean
     */
    protected abstract void initBean(BeanDefinition beanDefinition);

    /**
     * beanClass : bean的id
     * return bean的实例对象列表
     *
     * @param beanClass
     */
    @Override
    public List<Object> getBeanByBusinessType(Class<?> beanClass) {
        List<Object> beans = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionMap.keySet()) {
            if (beanClass.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 初始化bean
     */
    private void initBeanPostProcessor(BeanDefinition beanDefinition) {
        Object bean = beanDefinition.getBean();
        String beanName = beanDefinition.getId();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        beanDefinition.setBean(bean);
    }
}
