package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicUserRoleService;
import com.roffer.web.modules.sys.entity.BasicUserRole;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roffer.common.http.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/basicUserRole")
/**
 * @description 用户角色
 * @author roffer
 * @date 2022-04-25
 */
@Api("用户角色相关")
public class BasicUserRoleController {
    @Resource
    private BasicUserRoleService basicUserRoleService;

    @ApiOperation(value = "根据Id获取用户角色")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicUserRole basicUserRole = basicUserRoleService.getById(id);
        return R.ok().data("basicUserRole", basicUserRole);
    }

    @ApiOperation(value = "获取全部用户角色")
    @PostMapping("/list")
    public Object list() {
        return R.ok().data("list", basicUserRoleService.list());
    }

    @ApiOperation(value = "分页用户角色")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicUserRole> basicUserRolePage = null;
        if(null == pageNum && null == pageSize){
            basicUserRolePage = new Page<>();
        }else{
            basicUserRolePage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper queryWrapper = new QueryWrapper();

        basicUserRoleService.page(basicUserRolePage,queryWrapper);

        long total = basicUserRolePage.getTotal();
        List<BasicUserRole> basicUserRoleList = basicUserRolePage.getRecords();

        return R.ok().data("total", total).data("list", basicUserRoleList);
    }

    @ApiOperation(value = "添加用户角色")
    @PostMapping("/save")
    public Object save(BasicUserRole basicUserRole) {
        basicUserRoleService.save(basicUserRole);
        return R.ok();
    }

    @ApiOperation(value = "更新用户角色")
    @PostMapping("/update")
    public Object update(BasicUserRole basicUserRole) {
        basicUserRoleService.updateById(basicUserRole);
        return R.ok();
    }

    @ApiOperation(value = "删除用户角色")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicUserRoleService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除用户角色")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicUserRoleService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }
}
