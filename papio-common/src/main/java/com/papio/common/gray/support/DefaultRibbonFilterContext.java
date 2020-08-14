package com.papio.common.gray.support;

import com.papio.common.constant.GrayConstant;
import com.papio.common.gray.api.RibbonFilterContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * DefaultRibbonFilterContext
 * 灰度负载过滤上下文默认实现类
 *
 * @author papio
 * @date 2020/7/21
 **/
public class DefaultRibbonFilterContext implements RibbonFilterContext {
    private final Map<String, String> attributes = new HashMap<>();

    @Override
    public RibbonFilterContext add(String key, String value) {
        // 若version版本号为空，则赋值默认版本号
        if (key.equals(GrayConstant.VERSION) && StringUtils.isBlank(value)) {
            value = GrayConstant.DEFAULT_VERSION;
        }
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
