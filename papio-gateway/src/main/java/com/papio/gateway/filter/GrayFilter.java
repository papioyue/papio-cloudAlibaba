package com.papio.gateway.filter;

import com.papio.common.constant.GrayConstant;
import com.papio.common.gray.support.RibbonFilterContextHolder;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author papio
 * @date 2020/7/21
 **/
public class GrayFilter implements GlobalFilter, Ordered {

    /**
     * 值越大，有限制越低
     * @return int
     */
    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求头header中的version版本号
        String version = exchange.getRequest().getHeaders().getFirst(GrayConstant.VERSION);
        RibbonFilterContextHolder.getCurrentContext().add(GrayConstant.VERSION, version);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            RibbonFilterContextHolder.getCurrentContext().remove(GrayConstant.VERSION);
        }));
    }

}
