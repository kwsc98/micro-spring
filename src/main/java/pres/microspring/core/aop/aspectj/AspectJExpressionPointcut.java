package pres.microspring.core.aop.aspectj;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;
import pres.microspring.core.aop.ClassFilter;
import pres.microspring.core.aop.MethodMatcher;
import pres.microspring.core.aop.support.AbstractExpressionPointcut;
import pres.microspring.core.ioc.factory.BeanFactory;
import pres.microspring.core.ioc.factory.BeanFactoryAware;
import pres.microspring.core.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Aspect实现类
 */
public class AspectJExpressionPointcut extends AbstractExpressionPointcut implements ClassFilter, MethodMatcher, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(AspectJExpressionPointcut.class);

    /**
     * Aspect支持的匹配规则
     */
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    /**
     * Aspect核心匹配类
     */
    private PointcutExpression pointcutExpression;

    /**
     * beanFactory，用于对bean的一些匹配（暂无使用）
     */
    private BeanFactory beanFactory;


    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }


    /**
     * 获取Aspect类匹配器
     */
    @Override
    public ClassFilter getClassFilter() {
        obtainPointcutExpression();
        return this;
    }

    /**
     * 获取Aspect方法匹配器
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        obtainPointcutExpression();
        return this;
    }

    /**
     * 判断当前传入类是否符合匹配
     *
     * @param targetClass 候选的目标类
     * @return 通知是否应该应用于给定的目标类
     */
    @Override
    public boolean matches(Class<?> targetClass) {
        PointcutExpression pointcutExpression = obtainPointcutExpression();
        try {
            return pointcutExpression.couldMatchJoinPointsInType(targetClass);
        } catch (Throwable ex) {
            logger.debug("PointcutExpression matching rejected target class", ex);
        }
        return false;
    }

    /**
     * 判断传入的方法是否符合匹配
     *
     * @param method      候选方法
     * @param targetClass 目标类
     * @return 这个方法是否静态匹配
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        PointcutExpression pointcutExpression = obtainPointcutExpression();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        return shadowMatch.alwaysMatches();
    }

    /**
     * 判断这个MethodMatcher是动态的吗
     */
    @Override
    public boolean isRuntime() {
        return false;
    }

    /**
     * 通过动态的方式进行匹配例如参数
     *
     * @param method      候选方法
     * @param targetClass 目标类
     * @param args        参数列表
     * @return 这个方法是否匹配
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }

    /**
     * 获取PointcutExpression
     */
    private PointcutExpression obtainPointcutExpression() {
        if (getExpression() == null) {
            throw new IllegalStateException("Must set property 'expression' before attempting to match");
        }
        if (this.pointcutExpression == null) {
            //判断如果PointcutExpression还未实例化则进行实例化
            this.pointcutExpression = buildPointcutExpression();
        }
        return this.pointcutExpression;
    }

    /**
     * 实例化PointcutExpression
     */
    private PointcutExpression buildPointcutExpression() {
        PointcutParser parser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(SUPPORTED_PRIMITIVES);
        return parser.parsePointcutExpression(getExpression());
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
