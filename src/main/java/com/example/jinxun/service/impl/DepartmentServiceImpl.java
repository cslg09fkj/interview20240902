package com.example.jinxun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jinxun.entity.Department;
import com.example.jinxun.mapper.DepartmentMapper;
import com.example.jinxun.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public IPage<Department> getDepartmentsByPage(Page<Department> page) {
        return baseMapper.selectPage(page, null);
    }

    @Override
    public Boolean isDepartmentIdValid(Long id) {
        Optional<Department> optionalDepartment = Optional.ofNullable(getById(id));

        // 判断部门是否存在
        return optionalDepartment.isPresent();
    }


}