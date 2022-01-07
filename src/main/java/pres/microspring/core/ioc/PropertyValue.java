package pres.microspring.core.ioc;


/**
 * micro-spring
 * 2021/12/31 13:59
 *
 * @author wangsicheng
 * @since
 **/
public class PropertyValue {

    String name;
    String value;
    String ref;
    boolean isRef = false;

    public PropertyValue() {
    }

    public static PropertyValue init() {
        return new PropertyValue();
    }

    public String getName() {
        return name;
    }

    public PropertyValue setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PropertyValue setValue(String value) {
        this.value = value;
        return this;
    }

    public String getRef() {
        return ref;
    }

    public PropertyValue setRef(String ref) {
        if(ref==null || ref.length()<=0){
            return this;
        }
        this.isRef = true;
        this.ref = ref;
        return this;
    }

    public boolean isRef() {
        return isRef;
    }
}
