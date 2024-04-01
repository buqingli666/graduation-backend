package com.oit.service;

import com.oit.dto.UserLoginDTO;
import com.oit.entity.User;

/**
 * @Author: buqingli
 * @Date: 2024/04/01/11:13
 * @Description: C端用户相关服务层
 */

public interface UserService {

    /*
     * @Author buqingli
     * @Date 2024/4/1 11:23
     * @Description 微信登录
     **/

    User wxLogin(UserLoginDTO userLoginDTO);

}
