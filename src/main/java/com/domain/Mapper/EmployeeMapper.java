package com.domain.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
