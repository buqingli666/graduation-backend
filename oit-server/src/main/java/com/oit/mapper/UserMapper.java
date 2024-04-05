package com.oit.mapper;

import com.oit.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/1 11:29
     * @Description 根据openid查询用户
     **/

    User getByOpenid(String openid);


    /*
     * @Author buqingli
     * @Date 2024/4/1 11:30
     * @Description 插入数据
     **/

    void insert(User user);

    /*
     * @Author buqingli
     * @Date 2024/4/1 11:30
     * @Description 根据id查询用户
     **/

    User getById(Long userId);

    /*
     * @Author buqingli
     * @Date 2024/4/5 16:38
     * @Description 根据动态条件统计用户数量
     **/

    Integer countByMap(Map map);
}
