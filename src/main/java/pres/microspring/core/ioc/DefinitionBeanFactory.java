package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/30 18:22
 *
 * @author wangsicheng
 * @since
 **/
public class DefinitionBeanFactory extends AbstractBeanFactory{
    /**
     * 实例化bean
     */
    @Override
    protected Object initBean(BeanDefinition beanDefinition) {
        try{
            return Class.forName(beanDefinition.getClassName()).newInstance();
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }
}
