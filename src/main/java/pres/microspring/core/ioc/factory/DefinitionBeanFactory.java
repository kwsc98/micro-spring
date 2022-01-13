package pres.microspring.core.ioc.factory;


import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.PropertyValue;
import pres.microspring.core.ioc.PropertyValues;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarOutputStream;

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
    protected void initBean(BeanDefinition beanDefinition) {
        try {
            Object beanObject = Class.forName(beanDefinition.getClassName()).newInstance();
            //先提前将实例化的beanObject放入beanDefinition,之后延迟加载的bean初始化就可以直接获取beanObject
            beanDefinition.setBean(beanObject);
            //初始化beanObject
            initPropertyValues(beanObject, beanDefinition.getPropertyValues());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPropertyValues(Object beanObject, PropertyValues propertyValues) throws Exception {
        if (beanObject instanceof BeanFactoryAware) {
            ((BeanFactoryAware) beanObject).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value;
            if (propertyValue.isRef()) {
                value = getBean(propertyValue.getRef());
            } else {
                value = propertyValue.getValue();
            }
            try {
                Field field = beanObject.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(beanObject, value);
            } catch (NoSuchFieldException e) {
                Method[] methodList = beanObject.getClass().getMethods();
                String methodName = "set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1);
                Method method = Arrays.stream(methodList)
                        .filter(e1 -> e1.getName().equals(methodName))
                        .findFirst().orElse(null);
                System.out.println();
                assert method != null;
                method.setAccessible(true);
                method.invoke(beanObject, value);
                System.out.println();
            }
        }
    }
}
