package com.example.jinxun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jinxun.dto.DispatchRequest;
import com.example.jinxun.entity.WorkOrder;
import com.example.jinxun.mapper.WorkOrderMapper;
import com.example.jinxun.service.DepartmentService;
import com.example.jinxun.service.WorkOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements WorkOrderService {

    @Resource
    private DepartmentService departmentService;

    @Override
    public IPage<WorkOrder> getWorkOrdersByPage(Page<WorkOrder> page) {
        // 这里可以添加一些额外的查询条件
        return baseMapper.selectPage(page, null);
    }

    @Override
    public void fenpei(DispatchRequest request)  throws IllegalArgumentException{
        // 验证部门 ID 是否有效
        if (!departmentService.isDepartmentIdValid(request.getDepartmentId())) {
            throw new IllegalArgumentException("部门 ID 无效");
        }

        // 查询工单
        WorkOrder workOrder = getById(request.getWorkOrderId());
        if (workOrder == null) {
            throw new IllegalArgumentException("工单不存在");
        }

        // 设置分派时间
        workOrder.setFenpaiTime(LocalDateTime.now());

        // 设置处理部门信息
        workOrder.setHandleDeptId(request.getDepartmentId());

        // 更新工单
        updateById(workOrder);
    }
}