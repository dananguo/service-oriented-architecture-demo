package com.soa.zuul.library.support;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author 19028
 * @date 2019/12/21 23:52
 */

public class RibbonFilterContextHolder {
    private static final TransmittableThreadLocal<RibbonFilterContext> contextHolder = new TransmittableThreadLocal<RibbonFilterContext>() {
        @Override
        protected RibbonFilterContext initialValue() {
            return new DefaultRibbonFilterContext();
        }
    };


    public static RibbonFilterContext getCurrentContext() {
        return contextHolder.get();
    }


    public static void clearCurrentContext() {
        contextHolder.remove();
    }
}
