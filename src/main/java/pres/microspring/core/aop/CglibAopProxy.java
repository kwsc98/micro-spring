package pres.microspring.core.aop;


import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import java.io.Serializable;

/**
 * micro-spring
 * 2022/1/11 16:18
 *
 * @author wangsicheng
 * @since
 **/
final public class CglibAopProxy implements AopProxy,Serializable  {

    private final AdvisedSupport advisedSupport;

    public CglibAopProxy (AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }
    /**
     * Create a new proxy object.
     */
    @Override
    public Object getProxy() {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(this.advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallbacks(getCallback());
        return enhancer.create();
    }


    public Callback[] getCallback() {
        Callback[] callbacks = new Callback[this.advisedSupport.getMethodSupporyList().size()];
        return null;
    }
}
