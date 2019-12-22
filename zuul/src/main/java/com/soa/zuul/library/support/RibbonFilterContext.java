package com.soa.zuul.library.support;

import java.util.Map;

/**
 * @author 19028
 * @date 2019/12/21 23:53
 */

public interface RibbonFilterContext {
    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();
}
