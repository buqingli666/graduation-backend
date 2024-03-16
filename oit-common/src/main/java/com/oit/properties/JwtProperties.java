package com.oit.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:28
 * @Description jwt 配置
 **/

@Component
@ConfigurationProperties(prefix = "oit.jwt")
@Data
public class JwtProperties {

    /*
     * @Author buqingli
     * @Date 2024/3/16 13:12
     * @Description 管理端员工生成 jwt 令牌相关配置
     **/

    private String adminSecretKey;

    private long adminTtl;

    private String adminTokenName;

    /*
     * @Author buqingli
     * @Date 2024/3/16 13:12
     * @Description 用户端微信用户生成 jwt 令牌相关配置
     **/

    private String userSecretKey;

    private long userTtl;

    private String userTokenName;

}
