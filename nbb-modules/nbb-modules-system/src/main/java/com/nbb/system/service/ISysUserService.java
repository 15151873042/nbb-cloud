package com.nbb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nbb.system.api.domain.entity.SysUser;

import java.util.Optional;

/**
 * 用户 业务层
 * 
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);

}
