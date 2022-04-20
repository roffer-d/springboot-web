package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicRoleMenuService;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;

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
@RequestMapping("/basicRoleMenu")
/**
 * @description 角色菜单
 * @author roffer
 * @date 2022-04-20
 */
@Api("角色菜单相关")
public class BasicRoleMenuController {
    @Resource
    private BasicRoleMenuService basicRoleMenuService;

    @ApiOperation(value = "根据Id获取角色菜单")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicRoleMenu basicRoleMenu = basicRoleMenuService.getById(id);
        return R.ok().data("basicRoleMenu", basicRoleMenu);
    }

    @ApiOperation(value = "获取全部角色菜单")
    @PostMapping("/list")
    public Object list() {
        return R.ok().data("list", basicRoleMenuService.list());
    }

    @ApiOperation(value = "分页角色菜单")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicRoleMenu> basicRoleMenuPage = null;
        if(null == pageNum && null == pageSize){
            basicRoleMenuPage = new Page<>();
        }else{
            basicRoleMenuPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        //queryWrapper.like("basicRoleMenu",${basicRoleMenu})
        basicRoleMenuService.page(basicRoleMenuPage,queryWrapper);

        long total = basicRoleMenuPage.getTotal();
        List<BasicRoleMenu> basicRoleMenuList = basicRoleMenuPage.getRecords();

        return R.ok().data("total", total).data("list", basicRoleMenuList);
    }

    @ApiOperation(value = "添加角色菜单")
    @PostMapping("/save")
    public Object save(BasicRoleMenu basicRoleMenu) {
        basicRoleMenuService.save(basicRoleMenu);
        return R.ok();
    }

    @ApiOperation(value = "更新角色菜单")
    @PostMapping("/update")
    public Object update(BasicRoleMenu basicRoleMenu) {
        basicRoleMenuService.updateById(basicRoleMenu);
        return R.ok();
    }

    @ApiOperation(value = "删除角色菜单")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicRoleMenuService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除角色菜单")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicRoleMenuService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }
}
