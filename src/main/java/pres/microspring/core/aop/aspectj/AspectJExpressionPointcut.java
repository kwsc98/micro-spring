/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * Spring implementation
 * that uses the AspectJ weaver to evaluate a pointcut expression.
 *
 * <p>The pointcut expression value is an AspectJ expression. This can
 * reference other pointcuts and use composition and other operations.
 *
 * <p>Naturally, as this is to be processed by Spring AOP's proxy-based model,
 * only method execution pointcuts are supported.
 *
 * @author Rob Harrop
 * @author Adrian Colyer
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Ramnivas Laddad
 * @author Dave Syer
 * @since 2.0
 */
public class AspectJExpressionPointcut extends AbstractExpressionPointcut implements ClassFilter, MethodMatcher, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(AspectJExpressionPointcut.class);


    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();


    private PointcutExpression pointcutExpression;

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
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never {@code null})
     */
    @Override
    public ClassFilter getClassFilter() {
        obtainPointcutExpression();
        return this;
    }

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never {@code null})
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        obtainPointcutExpression();
        return this;
    }

    /**
     * 切入点应该应用到给定的接口或目标类?
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
     * 静态检查给定方法是否匹配。
     * <p>如果返回{@code false}或如果{@link #isRuntime()}
     * 方法返回{@code false}，没有运行时检查(即没有
     *
     * @param method      候选方法
     * @param targetClass 目标类
     * @return 这个方法是否静态匹配
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        PointcutExpression pointcutExpression = obtainPointcutExpression();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * 判断这个MethodMatcher是动态的吗，也就是说，必须对
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
     * Check whether this pointcut is ready to match,
     * lazily building the underlying AspectJ pointcut expression.
     */
    private PointcutExpression obtainPointcutExpression() {
        if (getExpression() == null) {
            throw new IllegalStateException("Must set property 'expression' before attempting to match");
        }
        if (this.pointcutExpression == null) {
            this.pointcutExpression = buildPointcutExpression();
        }
        return this.pointcutExpression;
    }


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
