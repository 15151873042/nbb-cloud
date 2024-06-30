package com.nbb.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nbb.system.api.domain.entity.SysRole;
import com.nbb.system.domain.entity.SysUserRole;
import com.nbb.system.mapper.SysRoleMapper;
import com.nbb.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return null;
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> roles = this.getBaseMapper().selectRolePermissionByUserId(userId);

        return roles.stream()
                .flatMap(role -> Arrays.stream(role.getRoleKey().trim().split(",")))
                .collect(Collectors.toSet());
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return null;
    }

    @Override
    public List<Long> selectRoleListByUserId(Long userId) {
        return null;
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return null;
    }

    @Override
    public boolean checkRoleNameUnique(SysRole role) {
        return false;
    }

    @Override
    public boolean checkRoleKeyUnique(SysRole role) {
        return false;
    }

    @Override
    public void checkRoleAllowed(SysRole role) {

    }

    @Override
    public void checkRoleDataScope(Long... roleIds) {

    }

    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return 0;
    }

    @Override
    public int insertRole(SysRole role) {
        return 0;
    }

    @Override
    public int updateRole(SysRole role) {
        return 0;
    }

    @Override
    public int updateRoleStatus(SysRole role) {
        return 0;
    }

    @Override
    public int authDataScope(SysRole role) {
        return 0;
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return 0;
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        return 0;
    }

    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        return 0;
    }

    @Override
    public int deleteAuthUsers(Long roleId, Long[] userIds) {
        return 0;
    }

    @Override
    public int insertAuthUsers(Long roleId, Long[] userIds) {
        return 0;
    }
}
