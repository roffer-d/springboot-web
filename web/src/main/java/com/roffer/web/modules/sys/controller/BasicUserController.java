package com.roffer.web.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roffer.common.http.R;
import com.roffer.web.annotation.LogRecords;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.entity.BasicUserRole;
import com.roffer.web.modules.sys.service.BasicUserService;
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
@RequestMapping("/basicUser")
/**
 * @description 用户
 * @author roffer
 * @date 2022-04-25
 */
@Api("用户相关")
public class BasicUserController {
    @Resource
    private BasicUserService basicUserService;

    @ApiOperation(value = "根据Id获取用户")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicUser basicUser = basicUserService.getById(id);
        return R.ok().data("basicUser", basicUser);
    }

    @ApiOperation(value = "获取全部用户")
    @PostMapping("/list")
    public Object list() {
        QueryWrapper<BasicUser> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");
        return R.ok().data("list", basicUserService.list());
    }

    @ApiOperation(value = "分页用户")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String account,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicUser> basicUserPage = null;
        if(null == pageNum && null == pageSize){
            basicUserPage = new Page<>();
        }else{
            basicUserPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(account)) {
            queryWrapper.like("account", account);
        }
        if (StringUtils.isNotBlank(password)) {
            queryWrapper.like("password", password);
        }
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.like("email", email);
        }
        if (StringUtils.isNotBlank(status)) {
            queryWrapper.like("status", status);
        }

        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");

        basicUserService.page(basicUserPage,queryWrapper);

        long total = basicUserPage.getTotal();
        List<BasicUser> basicUserList = basicUserPage.getRecords();

        return R.ok().data("total", total).data("list", basicUserList);
    }

    @LogRecords(remark = "添加用户",action = LogRecords.OperLogEnum.ADD)
    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public Object save(BasicUser basicUser) {
        basicUserService.save(basicUser);
        return R.ok();
    }

    @LogRecords(remark = "更新用户",action = LogRecords.OperLogEnum.EDIT)
    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public Object update(BasicUser basicUser) {
        basicUserService.updateById(basicUser);
        return R.ok();
    }

    @LogRecords(remark = "删除用户",action = LogRecords.OperLogEnum.DELETE)
    @ApiOperation(value = "删除用户")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicUserService.removeUserAndRole(id);
        return R.ok();
    }

    @LogRecords(remark = "批量删除用户",action = LogRecords.OperLogEnum.DELETE)
    @ApiOperation(value = "批量删除用户")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicUserService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }

    @ApiOperation(value = "获取用户角色")
    @PostMapping("/userRole")
    public Object userRole(@RequestParam String userId) {
        List<BasicUserRole> basicUserRoles = basicUserService.userRole(userId);
        return R.ok().data("list",basicUserRoles);
    }

    @ApiOperation(value = "保存用户角色")
    @LogRecords(remark = "保存用户角色",action = LogRecords.OperLogEnum.ADD)
    @PostMapping("/saveRole")
    public Object saveRole(@RequestParam String userId,@RequestParam String roleIds) {
        basicUserService.saveRole(userId,roleIds);
        return R.ok();
    }
}
