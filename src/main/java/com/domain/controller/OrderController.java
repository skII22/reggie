package com.domain.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.common.BaseContext;
import com.domain.common.R;
import com.domain.entity.Orders;
import com.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }
//    /**
//     * 详细订单
//     */
//    @GetMapping("/userPage")
//    public R<Page> page(int page,int pageSize){
//        log.info("page={},pageSize={}",page,pageSize);
//        Page pageInfo = new Page(page,pageSize);
//        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.orderByDesc(Orders::getOrderTime);
//        queryWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
//        orderService.page(pageInfo,queryWrapper);
//        return  R.success(pageInfo);
//    }

    /**
     * 订单明细
     * @param page pageSize
     */
    @GetMapping("/page")
//    public R<Page> page(int page,int pageSize){
//        Page pageInfo = new Page<>();
//        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.orderByDesc(Orders::getOrderTime);
//        queryWrapper.eq(Orders::getUserId,BaseContext.getCurrentId());
//        orderService.page(pageInfo,queryWrapper);
//        return R.success(pageInfo);
//    }
    public  R<Page> page(int page,int pageSize,Long number){
        Page pageInfo = new Page<Orders>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOrderTime);
        queryWrapper.eq(Orders::getUserId,BaseContext.getCurrentId());
        if (number != null){
        queryWrapper.eq(Orders::getId,number);
        }
        orderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @PutMapping
    public R satus(@RequestBody Orders orders){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId,orders.getId());
        Orders one = orderService.getOne(queryWrapper);
        Integer status = one.getStatus();
        if (status.equals(2)){
            one.setStatus(3);
        } else if (status.equals(3)) {
            one.setStatus(4);
        }
        orderService.updateById(one);
        return R.success("修改成功");
    }
}
