package com.roffer.web.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roffer.common.http.R;
import com.roffer.web.modules.sys.entity.BasicLog;
import com.roffer.web.modules.sys.service.BasicLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/basicLog")
/**
 * @description 操作日志
 * @author Roffer
 * @date 2022-05-16
 */
@Api(tags = "操作日志相关")
public class BasicLogController {
    private static final Logger logger = LoggerFactory.getLogger(BasicLogController.class);

    @Resource
    private BasicLogService basicLogService;

    @ApiOperation(value = "分页操作日志")
    @PostMapping("/listPage")
    public Object listPage(
            @ApiParam(value = "操作描述")
            @RequestParam(required = false) String remark,
            @ApiParam(value = "操作用户id")
            @RequestParam(required = false) String userId,
            @ApiParam(value = "操作用户")
            @RequestParam(required = false) String userName,
            @ApiParam(value = "页数")
            @RequestParam(required = false) Long pageNum,
            @ApiParam(value = "每页条数")
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
}
