package pres.microspring.core.aop;

/**
 * springAopFactory接口类
 **/
public interface AopProxyFactory {
	/**
	 * 获取对应的代理方式（JDK代理/cglib代理）
	 **/
	AopProxy createAopProxy(AdvisedSupport config);

}
