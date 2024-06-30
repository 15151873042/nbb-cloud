package com.nbb.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbb.system.api.domain.entity.SysUser;

/**
 * 用户表 数据层
 * 
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
}
