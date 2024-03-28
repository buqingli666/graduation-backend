package com.oit.mapper;

import com.oit.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/28 9:11
     * @Description 批量插入口味数据
     **/

    void insertBatch(List<DishFlavor> flavors);

}
