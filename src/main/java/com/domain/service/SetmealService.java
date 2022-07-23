package com.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.domain.dto.SetmealDto;
import com.domain.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {


    void remove(Long id);

    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    //根据id查询菜品信息和对应的口味信息
    public SetmealDto getByIdWithDish(Long id);

    /**
     * 修改套餐信息
     * @param setmealDto
     */

    public void updateWithDish(SetmealDto setmealDto);
}
