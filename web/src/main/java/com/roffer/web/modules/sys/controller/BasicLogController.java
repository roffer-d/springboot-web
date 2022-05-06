package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicLogService;
import com.roffer.web.modules.sys.entity.BasicLog;

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
@RequestMapping("/basicLog")
/**
 * @description 操作日志
 * @author Roffer
 * @date 2022-05-06
 */
@Api("操作日志相关")
public class BasicLogController {
    @Resource
    private BasicLogService basicLogService;

    @ApiOperation(value = "根据Id获取操作日志")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicLog basicLog = basicLogService.getById(id);
        return R.ok().data("basicLog", basicLog);
    }

    @ApiOperation(value = "获取全部操作日志")
    @PostMapping("/list")
    public Object list() {
        QueryWrapper<BasicLog> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");

        return R.ok().data("list", basicLogService.list(queryWrapper));
    }

    @ApiOperation(value = "分页操作日志")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicLog> basicLogPage = null;
        if(null == pageNum && null == pageSize){
            basicLogPage = new Page<>();
        }else{
            basicLogPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper<BasicLog> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(remark)) {
            queryWrapper.like("remark", remark);
        }
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.like("userId", userId);
        }
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("userName", userName);
        }
        queryWrapper.orderByDesc("create_time");

        basicLogService.page(basicLogPage,queryWrapper);

        long total = basicLogPage.getTotal();
        List<BasicLog> basicLogList = basicLogPage.getRecords();

        return R.ok().data("total", total).data("list", basicLogList);
    }

    @ApiOperation(value = "添加操作日志")
    @PostMapping("/save")
    public Object save(BasicLog basicLog) {
        basicLogService.save(basicLog);
        return R.ok();
    }

    @ApiOperation(value = "更新操作日志")
    @PostMapping("/update")
    public Object update(BasicLog basicLog) {
        basicLogService.updateById(basicLog);
        return R.ok();
    }

    @ApiOperation(value = "删除操作日志")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicLogService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除操作日志")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicLogService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }
}
