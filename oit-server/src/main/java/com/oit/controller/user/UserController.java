package com.oit.controller.user;

import com.oit.constant.JwtClaimsConstant;
import com.oit.dto.UserLoginDTO;
import com.oit.entity.User;
import com.oit.properties.JwtProperties;
import com.oit.result.Result;
import com.oit.service.UserService;
import com.oit.utils.JwtUtil;
import com.oit.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: buqingli
 * @Date: 2024/04/01/11:05
 * @Description: C端用户相关控制层
 */

@RestController
@RequestMapping(value = "/user/user", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "C端用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /*
     * @Author buqingli
     * @Date 2024/4/1 11:21
     * @Description 微信登录
     **/

    @PostMapping("/login")
    @ApiOperation("微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        log.info("微信用户登录:{}", userLoginDTO.getCode());

        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        //为微信用户生成 jwt 令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
