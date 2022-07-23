package com.domain.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
