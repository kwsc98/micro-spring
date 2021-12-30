package pres.microspring.core.ioc;


/**
 * @author kwsc98
 */
public class  BeanDefinition  {

    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
