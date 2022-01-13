package pres.microspring.core.aop;


import pres.microspring.core.aop.aspectj.AspectJExpressionPointcutAdvisor;
import pres.microspring.core.aopalliance.intercept.MethodInterceptor;
import pres.microspring.core.ioc.factory.AbstractBeanFactory;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.BeanFactoryAware;

import java.util.ArrayList;
import java.util.List;

/**
 * micro-spring
 * 2022/1/12 18:16
 *
 * @author wangsicheng
 * @since
 **/
public class AopProxyBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * bean初始化前置处理方法
     **/
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * bean初始化后置处理方法
     **/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        if (bean instanceof AopProxyFactory) {
            return bean;
        }
        List<Object> advisors = beanFactory.getBeanByBusinessType(AspectJExpressionPointcutAdvisor.class);
        List<MethodSuppory> supporyList = new ArrayList<>();
        for (Object advisor : advisors) {
            AspectJExpressionPointcutAdvisor expressionPointcutAdvisor = (AspectJExpressionPointcutAdvisor) advisor;
            if (expressionPointcutAdvisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                MethodSuppory methodSuppory = new MethodSuppory();
                methodSuppory.setMethodMatcher(expressionPointcutAdvisor.getPointcut().getMethodMatcher());
                methodSuppory.setMethodInterceptor((MethodInterceptor) expressionPointcutAdvisor.getAdvice());
                supporyList.add(methodSuppory);
            }
        }
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(bean));
        advisedSupport.setMethodSupporyList(supporyList);
        List<Object> aopFactoryList = beanFactory.getBeanByBusinessType(AopProxyFactory.class);
        if (supporyList.size() > 0 && aopFactoryList.size() > 0) {
            AopProxyFactory aopProxyFactory = (AopProxyFactory) aopFactoryList.get(0);
            bean = aopProxyFactory.createAopProxy(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
