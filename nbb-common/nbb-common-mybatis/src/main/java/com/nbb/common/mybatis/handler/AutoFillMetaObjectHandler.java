package com.nbb.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.nbb.common.security.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充字段
 * @see <a href="https://baomidou.com/guides/auto-fill-field/">https://baomidou.com/guides/auto-fill-field/</a>
 */
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    /**
     * 表数据新增时自动填充的字段（字段有原始值则忽略）
     * 实体中有一个字段开启插入填充（FieldFill.INSERT）就会回调
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);

        String username = SecurityUtils.getUsername().orElse("system");
        this.strictInsertFill(metaObject, "createBy", String.class, username);
        this.strictInsertFill(metaObject, "updateBy", String.class, username);
    }

    /**
     * 表数据更新时自动填充的字段（字段有原始值则忽略）
     * 实体中有一个字段开启插入填充（FieldFill.UPDATE）就会回调
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        String username = SecurityUtils.getUsername().orElse("system");
        this.strictUpdateFill(metaObject, "updateBy", String.class, username);
    }
}
