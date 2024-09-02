package com.example.jinxun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jinxun.dto.DispatchRequest;
import com.example.jinxun.entity.WorkOrder;

public interface WorkOrderService extends IService<WorkOrder> {
    IPage<WorkOrder> getWorkOrdersByPage(Page<WorkOrder> page);

    void fenpei(DispatchRequest request);
}
