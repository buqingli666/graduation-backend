package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

/*
 * @Author buqingli
 * @Date 2024/3/14 15:19
 * @Description 员工管理服务层
 **/

public interface EmployeeService {

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:19
     * @Description 员工登录
     **/

    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:20
     * @Description 新增员工
     **/

    void save(EmployeeDTO employeeDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:20
     * @Description 员工分页查询
     **/

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:20
     * @Description 启用禁用员工账号
     **/

    void startOrStop(Integer status, Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:20
     * @Description 根据id查询员工信息
     **/

    Employee getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:21
     * @Description 编辑员工信息
     **/

    void update(EmployeeDTO employeeDTO);
}
