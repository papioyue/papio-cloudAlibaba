package com.papio.common.gray.api;

import java.util.Map;

/**
 * RibbonFilterContext
 * 灰度负载上下文接口
 *
 * @author papio
 * @date 2020/7/21
 */
public interface RibbonFilterContext {

    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();
}
