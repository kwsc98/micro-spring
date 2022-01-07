package pres.microspring.core.dom;


import org.dom4j.Element;
import pres.microspring.core.ioc.BeanDefinition;
import pres.microspring.core.ioc.PropertyValue;
import java.util.Iterator;

/**
 * micro-spring
 * 2022/1/7 11:41
 *
 * @author wangsicheng
 * @since
 **/
public class BeanElementService {
    /**
     * fetch data by rule id
     *
     * @param element is element
     * @return BeanDefinition
     */
    public static BeanDefinition resolver(Element element) {
        String beanId = element.attributeValue("id");
        String beanClass = element.attributeValue("class");
        BeanDefinition beanDefinition = new BeanDefinition(beanId,beanClass);
        Iterator<?> iterator =  element.elementIterator();
        while (iterator.hasNext()){
            Element e = (Element) iterator.next();
            if("property".equals(e.getName())){
                PropertyValue propertyValue = resolverProperty(e);
                beanDefinition.getPropertyValues().add(propertyValue);
            }
        }
        return beanDefinition;
    }

    public static PropertyValue resolverProperty(Element element) {
        String name = element.attributeValue("name");
        String value = element.attributeValue("value");
        String ref = element.attributeValue("ref");
        return PropertyValue.init().setName(name).setValue(value).setRef(ref);
    }
}
