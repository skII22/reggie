package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.BaseContext;
import com.itheima.common.R;
import com.itheima.dto.DishDto;
import com.itheima.dto.SetmealDto;
import com.itheima.entity.Category;
import com.itheima.entity.Setmeal;
import com.itheima.entity.SetmealDish;
import com.itheima.service.SetmealDishService;
import com.itheima.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static com.itheima.common.R.success;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);

        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo =new Page<>(page,pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("删除套餐，id为：{}",ids);
        log.info(BaseContext.getCurrentId().toString());
        setmealService.removeWithDish(ids);

        return R.success("套餐删除成功");
    }

    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != setmeal.getCategoryId(),Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(null != setmeal.getStatus(),Setmeal::getStatus,1);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);

    }
    /**
     * 批量启用禁用
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@RequestParam Long ids,@PathVariable int status){
        Setmeal setmeal = setmealService.getById(ids);
        if (status==1){
            setmeal.setStatus(1);
        }else {
            setmeal.setStatus(0);
        }
        setmealService.updateById(setmeal);
        return R.success("售卖状态修改成功");
    }
    /**
     * 修改菜品信息
     */
    @PutMapping
    public R update(@RequestBody SetmealDto setmealDto){

            log.info(setmealDto.toString());
            setmealService.updateWithDish(setmealDto);
            return R.success("修改套餐成功！");

    }
    @GetMapping("/{id}")
    public R<SetmealDto> updatebyId(@PathVariable Long id){
        log.info("321");

        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return R.success(setmealDto);
    }



}
