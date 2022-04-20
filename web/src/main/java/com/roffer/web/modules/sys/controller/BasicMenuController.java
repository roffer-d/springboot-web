package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicMenuService;
import com.roffer.web.modules.sys.entity.BasicMenu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roffer.common.http.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/basicMenu")
/**
 * @description 菜单
 * @author roffer
 * @date 2022-04-20
 */
@Api("菜单相关")
public class BasicMenuController {
    @Resource
    private BasicMenuService basicMenuService;

    @ApiOperation(value = "根据Id获取菜单")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicMenu basicMenu = basicMenuService.getById(id);
        return R.ok().data("basicMenu", basicMenu);
    }

    @ApiOperation(value = "获取全部菜单")
    @PostMapping("/list")
    public Object list() {
        return R.ok().data("list", basicMenuService.list());
    }

    @ApiOperation(value = "分页菜单")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicMenu> basicMenuPage = null;
        if(null == pageNum && null == pageSize){
            basicMenuPage = new Page<>();
        }else{
            basicMenuPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        //queryWrapper.like("basicMenu",${basicMenu})
        basicMenuService.page(basicMenuPage,queryWrapper);

        long total = basicMenuPage.getTotal();
        List<BasicMenu> basicMenuList = basicMenuPage.getRecords();

        return R.ok().data("total", total).data("list", basicMenuList);
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("/save")
    public Object save(BasicMenu basicMenu) {
        basicMenuService.save(basicMenu);
        return R.ok();
    }

    @ApiOperation(value = "更新菜单")
    @PostMapping("/update")
    public Object update(BasicMenu basicMenu) {
        basicMenuService.updateById(basicMenu);
        return R.ok();
    }

    @ApiOperation(value = "删除菜单")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicMenuService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除菜单")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicMenuService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }
}
