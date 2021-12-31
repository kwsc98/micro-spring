package pres.microspring.core.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author kwsc98
 */
public abstract class AbstractBeanFactory implements BeanFactory{

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	@Override
	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinition.setBean(initBean(beanDefinition));
		beanDefinitionMap.put(name, beanDefinition);
	}

	/**
	 * 实例化bean
	 */
	protected abstract Object initBean(BeanDefinition beanDefinition);

}
