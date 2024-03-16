package com.oit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oit.constant.MessageConstant;
import com.oit.constant.PasswordConstant;
import com.oit.constant.StatusConstant;
import com.oit.dto.EmployeeDTO;
import com.oit.dto.EmployeeLoginDTO;
import com.oit.dto.EmployeePageQueryDTO;
import com.oit.entity.Employee;
import com.oit.exception.AccountLockedException;
import com.oit.exception.AccountNotFoundException;
import com.oit.exception.PasswordErrorException;
import com.oit.mapper.EmployeeMapper;
import com.oit.result.PageResult;
import com.oit.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/3/14 15:22
 * @Description 员工管理业务层
 **/

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:23
     * @Description 员工登录
     **/

    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus().equals(StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:25
     * @Description 新增员工
     **/

    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);
        //设置账号的状态，默认正常状态 1表示正常 0表示锁定
        employee.setStatus(StatusConstant.ENABLE);
        //设置密码，默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置当前记录的创建时间和修改时间
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //设置当前记录创建人 id 和修改人 id
        //employee.setCreateUser(BaseContext.getCurrentId());
        //employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.insert(employee);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:25
     * @Description 员工分页查询
     **/

    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // select * from employee limit 0,10
        // 开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total, records);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:25
     * @Description 启用禁用员工账号
     **/

    public void startOrStop(Integer status, Long id) {
        // update employee set status = ? where id = ?
        // Employee employee = new Employee();
        // employee.setStatus(status);
        // employee.setId(id);
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:26
     * @Description 根据id查询员工信息
     **/

    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("****");
        return employee;
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:26
     * @Description 编辑员工信息
     **/

    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(employee);
    }

}
