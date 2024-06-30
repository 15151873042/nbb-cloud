package com.nbb.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity基类
 * 
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;


    /** 创建者 */
    @TableField(value = "create_by", fill= FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @TableField(value = "create_time", fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新者 */
    @TableField(value = "update_by", fill= FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
    @TableField(value = "update_time", fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 备注 */
    private String remark;

}
