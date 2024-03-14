package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:27
     * @Description 根据用户名查询员工
     **/

    Employee getByUsername(String username);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:27
     * @Description 插入员工数据
     **/

    void insert(Employee employee);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:28
     * @Description 员工分页查询
     **/

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:28
     * @Description 根据主键动态修改属性
     **/

    void update(Employee employee);

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:28
     * @Description 根据id查询员工信息
     **/

    Employee getById(Long id);
}
