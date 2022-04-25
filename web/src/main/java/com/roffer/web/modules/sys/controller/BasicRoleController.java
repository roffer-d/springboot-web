package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicRoleService;
import com.roffer.web.modules.sys.entity.BasicRole;

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
@RequestMapping("/basicRole")
/**
 * @description 角色
 * @author roffer
 * @date 2022-04-25
 */
@Api("角色相关")
public class BasicRoleController {
    @Resource
    private BasicRoleService basicRoleService;

    @ApiOperation(value = "根据Id获取角色")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicRole basicRole = basicRoleService.getById(id);
        return R.ok().data("basicRole", basicRole);
    }

    @ApiOperation(value = "获取全部角色")
    @PostMapping("/list")
    public Object list() {
        return R.ok().data("list", basicRoleService.list());
    }

    @ApiOperation(value = "分页角色")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicRole> basicRolePage = null;
        if(null == pageNum && null == pageSize){
            basicRolePage = new Page<>();
        }else{
            basicRolePage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }

        basicRoleService.page(basicRolePage,queryWrapper);

        long total = basicRolePage.getTotal();
        List<BasicRole> basicRoleList = basicRolePage.getRecords();

        return R.ok().data("total", total).data("list", basicRoleList);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/save")
    public Object save(BasicRole basicRole) {
        basicRoleService.save(basicRole);
        return R.ok();
    }

    @ApiOperation(value = "更新角色")
    @PostMapping("/update")
    public Object update(BasicRole basicRole) {
        basicRoleService.updateById(basicRole);
        return R.ok();
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicRoleService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除角色")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicRoleService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }
}
