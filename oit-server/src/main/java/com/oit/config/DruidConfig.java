package com.oit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/*
 * @Author buqingli
 * @Date 2024/4/19 15:47
 * @Description: 解决druid日志报错:discard long time none received connection:xxx
 **/

@Configuration
@Slf4j
public class DruidConfig {
    @PostConstruct
    public void setProperties() {
        System.setProperty("druid.mysql.usePingMethod", "false");
        log.info("Druid相关配置已完成...");
    }
}
