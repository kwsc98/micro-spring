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
 * @since
 **/
public class DefinitionBeanFactory extends AbstractBeanFactory {
    /**
     * 实例化bean
     */
    @Override
    protected Object initBean(BeanDefinition beanDefinition) {
        try{
            Object beanObject = Class.forName(beanDefinition.getClassName()).newInstance();
            initPropertyValues(beanObject,beanDefinition.getPropertyValues());
            return beanObject;
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }

    private void initPropertyValues(Object beanObject, PropertyValues propertyValues) throws NoSuchFieldException, IllegalAccessException {
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()
             ) {
            Field field = beanObject.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(beanObject, propertyValue.getValue());
        }
    }
}
