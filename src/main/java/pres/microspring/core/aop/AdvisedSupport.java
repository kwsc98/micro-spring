package pres.microspring.core.aop;


import java.util.List;

/**
 * Aop代理支持类
 */
public class AdvisedSupport {
    /**
     * 代理目标类
     */
	private TargetSource targetSource;
    /**
     * aop类过滤处理支持类列表（支持多重代理）
     */
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
