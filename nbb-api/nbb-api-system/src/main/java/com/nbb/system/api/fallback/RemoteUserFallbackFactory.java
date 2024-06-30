package com.nbb.system.api.fallback;

import com.nbb.common.core.web.domain.RestResult;
import com.nbb.system.api.RemoteUserService;
import com.nbb.system.api.domain.entity.SysUser;
import com.nbb.system.api.domain.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable cause) {
        log.error(cause.getMessage(),cause);
        log.error("Circuit Breaker用户服务调用失败:{}", cause.getMessage());
        return new RemoteUserService() {

            @Override
            public RestResult<LoginUser> getUserInfo(String username, String source) {
                return RestResult.fail("获取用户失败:" + cause.getMessage());
            }

            @Override
            public RestResult<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return RestResult.fail("注册用户失败:" + cause.getMessage());
            }
        };
    }


}
