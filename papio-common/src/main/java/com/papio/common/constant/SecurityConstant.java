package com.papio.common.constant;

/**
 * SecurityConstant
 * 安全配置常量
 * @author papio
 * @date 2020/7/20
 */
public interface SecurityConstant {

    /**
     * 认证url
     */
    String OAUTH_URL = "/oauth/";

    /**
     * token 名称
     */
    String ACCESS_TOKEN = "Authorization";

    /**
     * bearer 前缀
     */
    String BEARER_PREFIX = "Bearer ";

    /**
     * access 前缀
     */
    String ACCESS_PREFIX = "access:";

    /**
     * 系统固定不进行认证，直接放行的URL，供WebSecurityConfig、ResourceServerConfig公用
     */
    String[] PATTERN_URLS = {
            "/actuator/**",
            "/druid/**",

            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/swagger-ui.html",
            "/doc.html"
    };
}
