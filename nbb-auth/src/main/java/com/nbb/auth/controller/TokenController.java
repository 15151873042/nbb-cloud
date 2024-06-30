package com.nbb.auth.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.nbb.auth.domain.LoginBody;
import com.nbb.auth.service.SysLoginService;
import com.nbb.common.core.web.domain.AjaxResult;
import com.nbb.common.core.web.domain.RestResult;
import com.nbb.common.security.utils.SecurityUtils;
import com.nbb.system.api.RemoteUserService;
import com.nbb.system.api.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController {
//    @Autowired
//    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;


    @Autowired
    private NacosDiscoveryProperties properties;
    @Autowired
    private ServerProperties serverProperties;


    @RequestMapping("/test")
    public RestResult<?> test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("clusterName", properties.getClusterName());
        map.put("port", serverProperties.getPort());
        return RestResult.ok(map);
    }


    @PostMapping("login")
    public RestResult<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());

        // 用户登陆
        StpUtil.login(userInfo.getSysUser().getUserId());
        // 用户信息放入session
        SaSession session = StpUtil.getSession();
        session.set(SaSession.USER, userInfo);
        session.set(SaSession.PERMISSION_LIST, userInfo.getPermissions());
        session.set(SaSession.ROLE_LIST, userInfo.getRoles());

        String tokenValue = StpUtil.getTokenValue();
        Map<String, String> vo = MapUtil.of("access_token", tokenValue);
        return RestResult.ok(vo);
    }


    @DeleteMapping("logout")
    public RestResult<?> logout(HttpServletRequest request) {
        StpUtil.logout();
        return RestResult.ok();
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser().get();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", loginUser.getSysUser());
        ajax.put("roles", loginUser.getRoles());
        ajax.put("permissions", loginUser.getPermissions());
        return ajax;
    }

//    @DeleteMapping("logout")
//    public R<?> logout(HttpServletRequest request)
//    {
//        String token = SecurityUtils.getToken(request);
//        if (StringUtils.isNotEmpty(token))
//        {
//            String username = JwtUtils.getUserName(token);
//            // 删除用户缓存记录
//            AuthUtil.logoutByToken(token);
//            // 记录用户退出日志
//            sysLoginService.logout(username);
//        }
//        return R.ok();
//    }
//
//    @PostMapping("refresh")
//    public R<?> refresh(HttpServletRequest request)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser))
//        {
//            // 刷新令牌有效期
//            tokenService.refreshToken(loginUser);
//            return R.ok();
//        }
//        return R.ok();
//    }
//
//    @PostMapping("register")
//    public R<?> register(@RequestBody RegisterBody registerBody)
//    {
//        // 用户注册
//        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
//        return R.ok();
//    }
}
