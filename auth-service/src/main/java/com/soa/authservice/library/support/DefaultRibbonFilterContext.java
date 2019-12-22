package com.soa.authservice.library.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 19028
 * @date 2019/12/21 23:53
 */

public class DefaultRibbonFilterContext implements RibbonFilterContext {
    private final Map<String, String> attributes = new HashMap<String, String>();

    @Override
    public RibbonFilterContext add(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public String get(String key) {
        return attributes.get(key);
    }

    @Override
    public RibbonFilterContext remove(String key) {
        attributes.remove(key);
        return this;
    }

    @Override
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}

