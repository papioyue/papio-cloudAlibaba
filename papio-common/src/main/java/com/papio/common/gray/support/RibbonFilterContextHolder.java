package com.papio.common.gray.support;

import com.papio.common.gray.api.RibbonFilterContext;

/**
 * RibbonFilterContextHolder
 * 灰度负载过滤上下文持有类
 *
 * @author papio
 * @date 2020/7/21
 **/
public class RibbonFilterContextHolder {

    private static final ThreadLocal<RibbonFilterContext> CONTEXT_HOLDER = new InheritableThreadLocal() {
        @Override
        protected RibbonFilterContext initialValue() {
            return new DefaultRibbonFilterContext();
        }
    };

    public static RibbonFilterContext getCurrentContext() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearCurrentContext() {
        CONTEXT_HOLDER.remove();
    }
}
