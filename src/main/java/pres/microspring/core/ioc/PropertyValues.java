package pres.microspring.core.ioc;


import java.util.ArrayList;
import java.util.List;

/**
 * micro-spring
 * 2021/12/31 13:59
 *
 * @author wangsicheng
 **/
public class PropertyValues {
    private final List<PropertyValue> propertyValues;
    
    public PropertyValues(){
        propertyValues = new ArrayList<PropertyValue>();
    }
    
    public PropertyValues add(PropertyValue propertyValue){
         propertyValues.add(propertyValue);
         return this;
    }

    public List<PropertyValue> getPropertyValues(){
        return propertyValues;
    }

}
