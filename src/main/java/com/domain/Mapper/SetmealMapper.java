package com.domain.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.dto.SetmealDto;
import com.domain.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);
}