package com.oit.mapper;

import com.oit.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:17
     * @Description 条件查询
     **/

    List<AddressBook> list(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:24
     * @Description 新增地址
     **/

    void insert(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:24
     * @Description 根据id查询地址
     **/

    AddressBook getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:25
     * @Description 根据id修改地址
     **/

    void update(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:25
     * @Description 根据用id修改是否为默认地址
     **/

    void updateIsDefaultByUserId(AddressBook addressBook);

    /*
     * @Author buqingli
     * @Date 2024/4/2 11:26
     * @Description 根据id删除地址
     **/

    void deleteById(Long id);

}
