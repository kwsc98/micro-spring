package pres.microspring.core.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yihua.huang@dianping.com
 */
public class BeanFactory {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
	}

}
