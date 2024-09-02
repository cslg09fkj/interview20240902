package com.example.jinxun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.jinxun.entity.Department;
import com.example.jinxun.entity.WorkOrder;
import com.example.jinxun.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Tag(name = "部门管理")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    @Operation(summary = "新增部门")
    public Department create(@Valid @RequestBody Department department) {
        departmentService.save(department);
        return department;
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新部门")
    public Department update(@PathVariable Long id, @Valid @RequestBody Department department) {
        department.setDeptId(id);
        departmentService.updateById(department);
        return department;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门")
    public void delete(@PathVariable Long id) {
        departmentService.removeById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取单个部门")
    public Department getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @GetMapping
    @Operation(summary = "获取所有部门")
    public List<Department> getAll() {
        return departmentService.list();
    }


    @GetMapping("/search")
    public IPage<Department> getDepartmentsByPage(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "size", defaultValue = "10") int pageSize) {

        Page<Department> page = new Page<>(currentPage, pageSize);
        return departmentService.getDepartmentsByPage(page);
    }
}