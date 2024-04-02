package com.oit.service.impl;

import com.oit.context.BaseContext;
import com.oit.entity.AddressBook;
import com.oit.mapper.AddressBookMapper;
import com.oit.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/04/02/11:04
 * @Description: C端地址管理业务层
 */

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 查询当前登录用户的所有地址信息
     **/

    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        return addressBookMapper.list(addressBook);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 新增地址
     **/

    @Override
    public void save(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 根据id查询地址
     **/

    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 根据id修改地址
     **/

    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 设置默认地址
     **/

    @Override
    @Transactional
    public void setDefault(AddressBook addressBook) {
        //1、将当前用户的所有地址修改为非默认地址
        //SQL:update address_book set is_default = ? where user_id = ?
        addressBook.setIsDefault(0);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.updateIsDefaultByUserId(addressBook);

        //2、将当前地址改为默认地址
        //SQL:update address_book set is_default = ? where id = ?
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:15
     * @Description 根据id删除地址
     **/

    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
