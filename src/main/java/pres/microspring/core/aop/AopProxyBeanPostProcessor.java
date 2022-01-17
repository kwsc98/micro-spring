package pres.microspring.core.aop;


import pres.microspring.core.aop.aspectj.AspectJExpressionPointcutAdvisor;
import pres.microspring.core.aopalliance.intercept.MethodInterceptor;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.BeanFactoryAware;

import java.util.ArrayList;
import java.util.List;

/**
 * micro-spring
 * 2022/1/12 18:16
 * 自定义AOP初始化方法
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
        //判断如果bean是实现了AspectJExpressionPointcutAdvisor，MethodInterceptor，AopProxyFactory，则进行直接返回。
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        if (bean instanceof AopProxyFactory) {
            return bean;
        }
        //获取所有AspectJExpressionPointcutAdvisor子类
        List<Object> advisors = beanFactory.getBeanByBusinessType(AspectJExpressionPointcutAdvisor.class);
        List<MethodSuppory> supporyList = new ArrayList<>();
        for (Object advisor : advisors) {
            AspectJExpressionPointcutAdvisor expressionPointcutAdvisor = (AspectJExpressionPointcutAdvisor) advisor;
            //循环通过Pointcut().getClassFilter()（Aspect技术）判断当前类是否需要进行代理
            if (expressionPointcutAdvisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                //如果需要进行代理则生成MethodSuppory，设置对应的MethodMatcher（方法级匹配器），MethodInterceptor（代理方法）
                MethodSuppory methodSuppory = new MethodSuppory();
                methodSuppory.setMethodMatcher(expressionPointcutAdvisor.getPointcut().getMethodMatcher());
                methodSuppory.setMethodInterceptor((MethodInterceptor) expressionPointcutAdvisor.getAdvice());
                supporyList.add(methodSuppory);
            }
        }
        //生成AdvisedSupport，设置TargetSource目标对象，MethodSupporyList匹配代理列表
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(bean));
        advisedSupport.setMethodSupporyList(supporyList);
        //获取SpringAopFactory
        List<Object> aopFactoryList = beanFactory.getBeanByBusinessType(AopProxyFactory.class);
        //判断改bean是否需要进行代理
        if (supporyList.size() > 0 && aopFactoryList.size() > 0) {
            //如果需要代理则选择aopProxyFactory，bean调用接口获取对应代理类
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
