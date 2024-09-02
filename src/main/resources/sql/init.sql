CREATE TABLE `work_order` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `order_no` VARCHAR(255) NOT NULL,
  `order_type` TINYINT NOT NULL COMMENT '0: 交办, 1: 直接答复, 3: 无效工单',
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `handle_dept_id` INT,
  `create_time` DATETIME NOT NULL,
  `fenpai_time` DATETIME,
  `is_overdue` TINYINT NOT NULL DEFAULT 0 COMMENT '0: 否, 1: 是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `department` (
  `dept_id` INT PRIMARY KEY,
  `dept_name` VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `department` (`dept_id`, `dept_name`) VALUES
(1, '技术部'),
(2, '客服部'),
(3, '市场部');