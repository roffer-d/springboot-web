package com.roffer.web.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.roffer.web.annotation.LogRecords;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.service.BasicRoleService;
import com.roffer.web.modules.sys.entity.BasicRole;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roffer.common.http.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/basicRole")
/**
 * @description 角色
 * @author roffer
 * @date 2022-04-25
 */
@Api(tags = "角色相关")
public class BasicRoleController {
    @Resource
    private BasicRoleService basicRoleService;

    @ApiOperation(value = "根据Id获取角色")
    @PostMapping("/getById")
    public Object getById(@ApiParam(value = "主键id") @RequestParam String id) {
        BasicRole basicRole = basicRoleService.getById(id);
        return R.ok().data("basicRole", basicRole);
    }

    @ApiOperation(value = "获取全部角色")
    @PostMapping("/list")
    public Object list() {
        QueryWrapper<BasicRole> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");
        return R.ok().data("list", basicRoleService.list(queryWrapper));
    }

    @ApiOperation(value = "分页角色")
    @PostMapping("/listPage")
    public Object listPage(
            @ApiParam(value = "角色名称")
            @RequestParam(required = false) String name,
            @ApiParam(value = "页码")
            @RequestParam(required = false) Long pageNum,
            @ApiParam(value = "每页条数")
            @RequestParam(required = false) Long pageSize) {

        Page<BasicRole> basicRolePage = null;
        if(null == pageNum && null == pageSize){
            basicRolePage = new Page<>();
        }else{
            basicRolePage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper<BasicRole> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }

        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");

        basicRoleService.page(basicRolePage,queryWrapper);

        long total = basicRolePage.getTotal();
        List<BasicRole> basicRoleList = basicRolePage.getRecords();

        return R.ok().data("total", total).data("list", basicRoleList);
    }

    @LogRecords(remark = "添加角色",action = LogRecords.OperLogEnum.ADD)
    @ApiOperation(value = "添加角色")
    @PostMapping("/save")
    public Object save(BasicRole basicRole) {
        basicRoleService.save(basicRole);
        return R.ok();
    }

    @LogRecords(remark = "更新角色",action = LogRecords.OperLogEnum.EDIT)
    @ApiOperation(value = "更新角色")
    @PostMapping("/update")
    public Object update(BasicRole basicRole) {
        basicRoleService.updateById(basicRole);
        return R.ok();
    }

    @LogRecords(remark = "删除角色",action = LogRecords.OperLogEnum.DELETE)
    @ApiOperation(value = "删除角色")
    @PostMapping("/delete")
    public Object delete(@ApiParam(value = "主键id") String id) {
        basicRoleService.removeRoleAndMenuByIds(id);
        return R.ok();
    }

    @LogRecords(remark = "批量删除角色",action = LogRecords.OperLogEnum.DELETE)
    @ApiOperation(value = "批量删除角色")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@ApiParam(value = "主键id，多个逗号隔开") @RequestParam String ids) {
        basicRoleService.removeRoleAndMenuByIds(ids);
        return R.ok();
    }

    @ApiOperation(value = "获取角色关联的权限")
    @PostMapping("/getRoleAuth")
    public Object getRoleAuth(@ApiParam(value = "角色id") @RequestParam String roleId) {
        List<BasicRoleMenu> roleAuth = basicRoleService.getRoleAuth(roleId);
        return R.ok().data("list",roleAuth);
    }

    @LogRecords(remark = "保存角色权限",action = LogRecords.OperLogEnum.ADD)
    @ApiOperation(value = "保存角色权限")
    @PostMapping("/saveRoleAuth")
    public Object saveRoleAuth(@ApiParam(value = "角色权限json数据") @RequestBody JSONObject auth) {
        basicRoleService.saveRoleAuth(auth);
        return R.ok();
    }
}
