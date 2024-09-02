package com.example.jinxun.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jinxun.config.RedisOrderGenerator;
import com.example.jinxun.dto.DispatchRequest;
import com.example.jinxun.entity.WorkOrder;
import com.example.jinxun.service.WorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "工单管理")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private RedisOrderGenerator redisOrderGenerator;

    @PostMapping
    @Operation(summary = "新增工单")
    public WorkOrder create(@Valid @RequestBody WorkOrder workOrder) {
        workOrder.setOrderNo(redisOrderGenerator.generateOrderId());
        workOrderService.save(workOrder);
        return workOrder;
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新工单")
    public WorkOrder update(@PathVariable Long id, @Valid @RequestBody WorkOrder workOrder) {
        workOrder.setId(id);
        //工单号不应该被修改
        workOrder.setOrderNo(null);
        workOrderService.updateById(workOrder);
        return workOrder;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除工单")
    public void delete(@PathVariable Long id) {
        workOrderService.removeById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取单个工单")
    public WorkOrder getById(@PathVariable Long id) {
        return workOrderService.getById(id);
    }

    @GetMapping
    @Operation(summary = "获取所有工单")
    public List<WorkOrder> getAll() {
        return workOrderService.list();
    }

    @GetMapping("/search")
    @Operation(summary = "工单分页查询")
    public IPage<WorkOrder> getWorkOrdersByPage(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "size", defaultValue = "10") int pageSize) {

        Page<WorkOrder> page = new Page<>(currentPage, pageSize);
        return workOrderService.getWorkOrdersByPage(page);
    }

    @PostMapping("/fenpai")
    @Operation(summary = "分派工单")
    public void fenpai(@RequestBody DispatchRequest request) throws IllegalArgumentException {
        workOrderService.fenpei(request);
    }
}
