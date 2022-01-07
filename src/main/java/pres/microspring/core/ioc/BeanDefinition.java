package pres.microspring.core.ioc;


/**
 * @author kwsc98
 */
public class BeanDefinition {


    private final String id;

    private final String className;

    private Object bean;

    private PropertyValues propertyValues;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
        this.bean = null;
        this.propertyValues = new PropertyValues();
    }


    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public Object getBean() {
        return bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
