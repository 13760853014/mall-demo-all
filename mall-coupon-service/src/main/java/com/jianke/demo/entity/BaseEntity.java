package com.jianke.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEntity<T> {

    @TableId(value = "id", type = IdType.AUTO)
    private T id;

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String lastModifiedBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastModifiedDate;

    @Version
    private Integer recordVersion;

    @TableLogic
    private Integer deleteFlag;
}
