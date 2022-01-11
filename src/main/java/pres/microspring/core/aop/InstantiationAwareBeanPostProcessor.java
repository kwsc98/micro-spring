package pres.microspring.core.aop;

import com.sun.istack.internal.Nullable;
import pres.microspring.core.exception.BeansException;

/**
 * micro-spring
 * 2022/1/10 11:15
 *
 * @author lanhaifeng
 * @since
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    @Nullable
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

}
