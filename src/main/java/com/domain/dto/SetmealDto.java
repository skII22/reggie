package com.domain.dto;

import com.domain.entity.SetmealDish;
import com.domain.entity.Setmeal;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;//套餐关联的菜品集合

    private String categoryName;//分类名称
}