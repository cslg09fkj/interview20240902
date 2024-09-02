package com.example.jinxun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jinxun.entity.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {
    /**
     * 查询7月每天的工单总量、超期率
     * 结果含义分别是：日期、工单总量、超期工单数量、超期率
     * @return
     */
    @Select({
            "SELECT DATE(create_time) AS date,",
            "       COUNT(*) AS total_orders,",
            "       SUM(is_overdue) AS overdue_orders,",
            "       CASE ",
            "           WHEN COUNT(*) = 0 THEN 0",
            "           ELSE SUM(is_overdue) / COUNT(*) * 100",
            "       END AS overdue_rate",
            "FROM work_order",
            "WHERE MONTH(create_time) = 7",
            "GROUP BY DATE(create_time)"
    })
    List<Map<String, Object>> get4();

    /**
     * 查询7月每个部门的工单总量、超期率（部门名称不可以重复）
     * 结果含义分别是：部门名称、工单总量、超期工单数量、超期率
     * @return
     */
    @Select({
            "SELECT d.dept_name,",
            "       COUNT(w.id) AS total_orders,",
            "       SUM(w.is_overdue) AS overdue_orders,",
            "       CASE ",
            "           WHEN COUNT(w.id) = 0 THEN 0",
            "           ELSE SUM(w.is_overdue) / COUNT(w.id) * 100",
            "       END AS overdue_rate",
            "FROM work_order w",
            "JOIN department d ON w.handle_dept_id = d.dept_id",
            "WHERE MONTH(w.create_time) = 7",
            "GROUP BY d.dept_name"
    })
    List<Map<String, Object>> get5();
}