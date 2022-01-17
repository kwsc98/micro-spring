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

    /**
     * 通过beanId（name）获取ioc容器里的bean
     */
    @Override
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        //判断beanFactory里当前bean是否已经实例化
        if (beanDefinition.getBean() == null) {
            //如果当前bean没有实例化，则对此bean进行实例化和初始化依赖注入
            initBean(beanDefinition);
            //初始化之后再对bean进行一些自定义处理比如Aop织入等等
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
     *
     * 通过bean类型获取相应的beanList
     *
     */
    @Override
    public List<Object> getBeanByBusinessType(Class<?> beanClass) {
        List<Object> beans = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionMap.keySet()) {
            //通过isAssignableFrom方法判断当前的beanClass是否是传入的class的子类或者接口实现类
            if (beanClass.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                //如果是则当作为匹配bean返回
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
        //获取BeanPostProcessor列表进行自定义处理
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        //将处理之后的bean重新set进ioc容器中
        beanDefinition.setBean(bean);
    }
}
