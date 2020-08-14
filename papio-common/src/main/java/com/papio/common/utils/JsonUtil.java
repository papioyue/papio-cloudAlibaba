package com.papio.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Json工具类
 * @author papio
 * @date 2020/7/21
 **/
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json对象转json字符串
     *
     * @param obj json对象
     * @return json字符串
     */
    public static String obj2Json(Object obj) {
        return obj2Json(obj, true);
    }

    /**
     * json对象转json字符串
     *
     * @param obj    json对象
     * @param pretty 是否格式化
     * @return json字符串
     */
    public static String obj2Json(Object obj, boolean pretty) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {
            // 使用 Gson 解析
            // final String json =  new GsonBuilder().setPrettyPrinting().create().toJson(object);

            // 使用 JackSon 解析
            String json;
            if (pretty) {
                // 格式化输出（默认）
                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                // 普通输出
                json = objectMapper.writeValueAsString(obj);
            }
            return json;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json字符串转json对象
     *
     * @param json  json字符串
     * @param clazz json对象类型
     * @return json对象
     */
    public static <T> T json2Obj(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || null == clazz) {
            return null;
        }
        try {
            T obj = objectMapper.readValue(json, clazz);
            return obj;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据key获取json字符串中的值
     *
     * @param json json字符串
     * @param key  键
     * @return 值
     */
    public static String getJsonValueByKey(String json, String key) {
        if (StringUtils.isAnyBlank(json, key)) {
            return null;
        }
        try {
            final JsonNode jsonNode = objectMapper.readTree(json);
            return getJsonValueByKey(jsonNode, key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据key获取jsonNode对象中的值
     *
     * @param json jsonNode对象
     * @param key  键
     * @return 值
     */
    private static String getJsonValueByKey(JsonNode json, String key) {
        if (null == json || StringUtils.isBlank(key)) {
            return null;
        }
        int index = key.indexOf('.');
        if (index == -1) {
            if (null != json.get(key)) {
                return json.get(key).toString();
            }
            return null;
        } else {
            String s1 = key.substring(0, index);
            String s2 = key.substring(index + 1);
            return getJsonValueByKey(json.get(s1), s2);
        }
    }
}
