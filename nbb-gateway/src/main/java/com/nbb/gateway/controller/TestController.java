package com.nbb.gateway.controller;

import com.nbb.common.core.web.domain.AjaxResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @RequestMapping("/test")
    public Mono<AjaxResult> test() {
        AjaxResult test = AjaxResult.success("test");
        return Mono.just(test);
    }
}
