package pres.microspring.core.aop;


import net.sf.cglib.proxy.MethodInterceptor;
import pres.microspring.core.aop.MethodMatcher;
import pres.microspring.core.aop.TargetSource;

import java.util.List;


public class AdvisedSupport {

	private TargetSource targetSource;

    private List<MethodSuppory> methodSupporyList;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<MethodSuppory> getMethodSupporyList() {
        return methodSupporyList;
    }

    public void setMethodSupporyList(List<MethodSuppory> methodSupporyList) {
        this.methodSupporyList = methodSupporyList;
    }
}
