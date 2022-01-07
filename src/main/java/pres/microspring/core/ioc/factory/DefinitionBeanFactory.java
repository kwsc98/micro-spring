package pres.microspring.core.ioc.factory;


import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.PropertyValue;
import pres.microspring.core.ioc.PropertyValues;

import java.lang.reflect.Field;

/**
 * micro-spring
 * 2021/12/30 18:22
 *
 * @author wangsicheng
 **/
public class DefinitionBeanFactory extends AbstractBeanFactory {
    /**
     * 实例化bean
     */
    @Override
    protected Object initBean(BeanDefinition beanDefinition) {
        try {
            Object beanObject = Class.forName(beanDefinition.getClassName()).newInstance();
            //先提前将实例化的beanObject放入beanDefinition,之后延迟加载的bean初始化就可以直接获取beanObject
            beanDefinition.setBean(beanObject);
            //初始化beanObject
            initPropertyValues(beanObject, beanDefinition.getPropertyValues());
            return beanObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initPropertyValues(Object beanObject, PropertyValues propertyValues) throws NoSuchFieldException, IllegalAccessException {
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Field field = beanObject.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            Object value = null;
            if (propertyValue.isRef()) {
                value = getBean(propertyValue.getRef());
            } else {
                value = propertyValue.getValue();
            }
            field.set(beanObject, value);
        }
    }
}
