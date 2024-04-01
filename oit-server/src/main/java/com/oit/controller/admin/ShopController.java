package com.oit.controller.admin;

import com.oit.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: buqingli
 * @Date: 2024/03/30/14:09
 * @Description: 管理端店铺状态管理控制层
 */

@RestController("adminShopController")
@RequestMapping(value = "/admin/shop", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "管理端店铺相关接口")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * @Author buqingli
     * @Date 2024/3/30 14:12
     * @Description 设置店铺的营业状态
     **/

    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result<?> setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态为:{}", status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/30 14:15
     * @Description 获取店铺的营业状态
     **/

    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        if (ObjectUtils.isEmpty(status)) {
            log.info("未获取到店铺的营业状态...");
        } else {
            log.info("获取到店铺的营业状态为:{}", status == 1 ? "营业中" : "打烊中");
        }
        return Result.success(status);
    }
}
