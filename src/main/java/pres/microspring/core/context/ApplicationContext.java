package pres.microspring.core.context;


/**
 * micro-spring
 * 2022/1/7 16:27
 *
 * @author lanhaifeng
 * @since
 **/
public interface ApplicationContext {
    /**
     * name : bean的id
     * return bean的实例对象
     */
    Object getBean(String name);
}
