package com.example.jinxun.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("work_order")
public class WorkOrder {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String orderNo;

    @NotNull(message = "必填")
    private Integer orderType; // 0: 交办, 1: 直接答复, 3: 无效工单

    @NotBlank(message = "必填")
    private String title;

    @NotBlank(message = "必填")
    private String content;

    private Long handleDeptId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private LocalDateTime fenpaiTime;

    private Integer isOverdue; // 0: 否, 1: 是


}