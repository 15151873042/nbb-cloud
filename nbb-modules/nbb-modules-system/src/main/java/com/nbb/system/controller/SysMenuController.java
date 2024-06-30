package com.nbb.system.controller;

import com.nbb.common.core.web.domain.AjaxResult;
import com.nbb.common.security.utils.SecurityUtils;
import com.nbb.system.domain.entity.SysMenu;
import com.nbb.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService menuService;


    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId().get();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}