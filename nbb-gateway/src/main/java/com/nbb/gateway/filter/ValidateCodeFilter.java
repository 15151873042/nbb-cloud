package com.nbb.gateway.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nbb.common.core.web.domain.RestResult;
import com.nbb.gateway.config.properties.CaptchaProperties;
import com.nbb.gateway.servcie.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤器
 *
 * @author ruoyi
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private CaptchaProperties captchaProperties;

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录/注册请求或验证码关闭，不处理
            if (!captchaProperties.getEnabled() || !captchaProperties.getValidateUrls().contains(request.getURI().getPath())) {
                return chain.filter(exchange);
            }

            try {
                String rspStr = resolveBodyFromRequest(request);
                JSONObject obj = JSONUtil.parseObj(rspStr);
                validateCodeService.checkCaptcha(obj.getStr(CODE), obj.getStr(UUID));
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.OK);
                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                RestResult<?> result = RestResult.fail(e.getMessage());

                DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(result).getBytes());
                return response.writeWith(Mono.just(dataBuffer));
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
