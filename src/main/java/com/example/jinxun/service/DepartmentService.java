package com.example.jinxun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jinxun.entity.Department;

public interface DepartmentService extends IService<Department> {
    IPage<Department> getDepartmentsByPage(Page<Department> page);

    Boolean isDepartmentIdValid(Long id);
}