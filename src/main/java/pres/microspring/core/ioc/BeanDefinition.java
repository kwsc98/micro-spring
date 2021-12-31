package pres.microspring.core.ioc;


/**
 * @author kwsc98
 */
public class  BeanDefinition  {


    private final String name;

    private final String className;

    private Object bean;

    private PropertyValues propertyValues;

    public BeanDefinition(String name,String className){
        this.name = name;
        this.className = className;
        this.bean = null;
        this.propertyValues = new PropertyValues();
    }


    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getName() {
        return name;
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
