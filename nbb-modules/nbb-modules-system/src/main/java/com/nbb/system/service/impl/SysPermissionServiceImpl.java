package com.nbb.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.nbb.system.api.domain.entity.SysRole;
import com.nbb.system.api.domain.entity.SysUser;
import com.nbb.system.service.ISysMenuService;
import com.nbb.system.service.ISysPermissionService;
import com.nbb.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            return Collections.singleton("admin");
        } else {
            return roleService.selectRolePermissionByUserId(user.getUserId());
        }
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(SysUser user) {

        // 管理员拥有所有权限
        if (user.isAdmin()) {
            return Collections.singleton("*:*:*");
        }

        // 未分配角色或分配角色全部被禁用
        List<SysRole> roles = user.getRoles();
        if (CollectionUtil.isEmpty(roles)) {
            return Collections.emptySet();
        }

        // 多角色设置permissions属性，以便数据权限匹配权限
        Set<String> perms = new HashSet<>();
        for (SysRole role : roles) {
            Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
            role.setPermissions(rolePerms);
            perms.addAll(rolePerms);
        }
        return perms;

    }
}
