package pres.microspring.core.ioc;


/**
* micro-spring
* 2021/12/31 13:59
*
*
*
* @author wangsicheng
* @since
**/
public class PropertyValue {

    String name;
    String value;

    public PropertyValue(String name,String value){
          this.name = name;
          this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
