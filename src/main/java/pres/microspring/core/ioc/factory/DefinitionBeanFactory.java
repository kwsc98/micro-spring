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
            //通过反射的方式实例化object
            Object beanObject = Class.forName(beanDefinition.getClassName()).newInstance();
            //先提前将实例化的beanObject放入beanDefinition,之后延迟加载的bean初始化就可以直接获取beanObject
            beanDefinition.setBean(beanObject);
            //初始化beanObject(设置相应参数)
            initPropertyValues(beanObject, beanDefinition.getPropertyValues());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPropertyValues(Object beanObject, PropertyValues propertyValues) throws Exception {
        //判断如果beanObject实现了BeanFactoryAware,则调用该实现接口
        if (beanObject instanceof BeanFactoryAware) {
            ((BeanFactoryAware) beanObject).setBeanFactory(this);
        }
        //进行参数依赖注入
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value;
            if (propertyValue.isRef()) {
                //判断如果是依赖注入则通过beanFactory获取bean
                value = getBean(propertyValue.getRef());
            } else {
                value = propertyValue.getValue();
            }
            try {
                //通过反射的方式获取Field，然后进行设置参数
                Field field = beanObject.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(beanObject, value);
            } catch (NoSuchFieldException e) {
                //如果没有根据参数名称找到对应Field，则通过set方法尝试调用方法进行设置
                Method[] methodList = beanObject.getClass().getMethods();
                String methodName = "set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1);
                Method method = Arrays.stream(methodList).filter(e1 -> e1.getName().equals(methodName)).findFirst().orElse(null);
                System.out.println();
                assert method != null;
                method.setAccessible(true);
                method.invoke(beanObject, value);
            }
        }
    }
}
