package com.oit.service;

import com.oit.entity.AddressBook;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/04/02/11:03
 * @Description: C端地址管理服务层
 */

public interface AddressBookService {

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:12
     * @Description 查询当前登录用户的所有地址信息
     **/

    List<AddressBook> list(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:12
     * @Description 新增地址
     **/

    void save(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:12
     * @Description 根据id查询地址
     **/

    AddressBook getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:13
     * @Description 根据id修改地址
     **/

    void update(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:13
     * @Description 设置默认地址
     **/

    void setDefault(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:14
     * @Description 根据id删除地址
     **/

    void deleteById(Long id);

}
