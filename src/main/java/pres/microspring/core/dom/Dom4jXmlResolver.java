package pres.microspring.core.dom;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pres.microspring.core.aopalliance.AopConfig;
import pres.microspring.core.ioc.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * micro-spring
 * 2022/1/7 14:59
 *
 * @author wangsicheng
 * @since
 **/
public class Dom4jXmlResolver {

    public static List<BeanDefinition> resolverXml(String path) {
         try{
             SAXReader reader = new SAXReader();
             Document document = reader.read(Dom4jXmlResolver.class.getResource(path));
             Element node = document.getRootElement();
             return resolverRootElement(node);
         }catch (Exception e){
             System.out.println("xmlPath解析错误:"+e);
             throw new RuntimeException();
         }
    }


    private static List<BeanDefinition> resolverRootElement(Element element) {
        List<BeanDefinition> list = new ArrayList<>();
        Iterator<?> iterable = element.elementIterator();
        while (iterable.hasNext()) {
            Element element1 = (Element) iterable.next();
            if ("bean".equals(element1.getName())) {
                list.add(BeanElementService.resolver(element1));
            } else if ("import".equals(element1.getName())) {
                String resourceValue = element1.attributeValue("resource");
                if (resourceValue != null && resourceValue.length() > 0) {
                    list.addAll(resolverXml(resourceValue));
                }
            } else if ("aspectj-autoproxy".equals(element1.getName())){
                AopConfig.setIsCglibAop(true);
            }
        }
        return list;
    }
}
