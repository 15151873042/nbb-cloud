package com.nbb.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.nbb.common.core.web.domain.AjaxResult;
import com.nbb.common.core.web.domain.RestResult;
import com.nbb.common.security.utils.SecurityUtils;
import com.nbb.system.api.domain.entity.SysUser;
import com.nbb.system.api.domain.model.LoginUser;
import com.nbb.system.service.ISysPermissionService;
import com.nbb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private ServerProperties serverProperties;

    @RequestMapping("/clusterName")
    public RestResult<Map<String, Object>> clusterName() {
        String clusterName = nacosDiscoveryProperties.getClusterName();
        Integer port = serverProperties.getPort();
        Map<String, Object> map = new HashMap<>();
        map.put("clusterName", clusterName);
        map.put("port", port);
        return RestResult.ok(map);
    }


    /**
     * 获取当前用户信息
     */
    @GetMapping("/info/{username}")
    public RestResult<LoginUser> info(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (ObjectUtil.isNull(sysUser)) {
            return RestResult.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser);
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return RestResult.ok(sysUserVo);
    }





    /**
     * 新增用户
     */
    @PostMapping("/add/save")
    public AjaxResult add(@RequestBody SysUser user) {
        long loginId = StpUtil.getLoginIdAsLong();
        user.setUserName("hupeng");
        user.setNickName("牛宝宝");
        user.setCreateTime(LocalDateTimeUtil.parse("2023-01-01 01:01:01", "yyyy-MM-dd HH:mm:ss"));
        user.setUpdateBy("root");

        userService.save(user);

        return AjaxResult.success();
    }
}
