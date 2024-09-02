package com.example.jinxun.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispatchRequest {
    @NotNull(message = "工单 ID 不能为空")
    private Long workOrderId;

    @NotNull(message = "处理部门 ID 不能为空")
    private Long departmentId;

    @NotBlank(message = "处理部门名称不能为空")
    private String departmentName;

    public DispatchRequest() {}

    public DispatchRequest(Long workOrderId, Long departmentId, String departmentName) {
        this.workOrderId = workOrderId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
}
