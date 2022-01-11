package pres.microspring.core.aop;


import com.sun.istack.internal.Nullable;

/**
 * micro-spring
 * 2022/1/10 11:05
 *
 * @author wangsicheng
 * @since
 **/
public interface BeanPostProcessor {
    /**
     * bean初始化前置处理方法
     **/
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }
    /**
     * bean初始化后置处理方法
     **/
    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
