package com.domain.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
