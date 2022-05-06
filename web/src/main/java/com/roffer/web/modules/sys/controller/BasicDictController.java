package com.roffer.web.modules.sys.controller;

import com.roffer.web.modules.sys.service.BasicDictService;
import com.roffer.web.modules.sys.entity.BasicDict;

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
@RequestMapping("/basicDict")
/**
 * @description 字典
 * @author Roffer
 * @date 2022-05-06
 */
@Api("字典相关")
public class BasicDictController {
    @Resource
    private BasicDictService basicDictService;

    @ApiOperation(value = "根据Id获取字典")
    @PostMapping("/getById")
    public Object getById(@RequestParam String id) {
        BasicDict basicDict = basicDictService.getById(id);
        return R.ok().data("basicDict", basicDict);
    }

    @ApiOperation(value = "获取全部字典")
    @PostMapping("/list")
    public Object list() {
        QueryWrapper<BasicDict> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");

        return R.ok().data("list", basicDictService.list(queryWrapper));
    }

    @ApiOperation(value = "分页字典")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicDict> basicDictPage = null;
        if(null == pageNum && null == pageSize){
            basicDictPage = new Page<>();
        }else{
            basicDictPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper<BasicDict> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.like("code", code);
        }
        if (StringUtils.isNotBlank(type)) {
            queryWrapper.like("type", type);
        }
        if (StringUtils.isNotBlank(remark)) {
            queryWrapper.like("remark", remark);
        }
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");

        basicDictService.page(basicDictPage,queryWrapper);

        long total = basicDictPage.getTotal();
        List<BasicDict> basicDictList = basicDictPage.getRecords();

        return R.ok().data("total", total).data("list", basicDictList);
    }

    @ApiOperation(value = "添加字典")
    @PostMapping("/save")
    public Object save(BasicDict basicDict) {
        basicDictService.save(basicDict);
        return R.ok();
    }

    @ApiOperation(value = "更新字典")
    @PostMapping("/update")
    public Object update(BasicDict basicDict) {
        basicDictService.updateById(basicDict);
        return R.ok();
    }

    @ApiOperation(value = "删除字典")
    @PostMapping("/delete")
    public Object delete(String id) {
        basicDictService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除字典")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicDictService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }

    @ApiOperation(value = "获取分类下的字典数据")
    @PostMapping("/getDict")
    public Object getDict(@RequestParam String type) {
        QueryWrapper<BasicDict> queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",type);

        return R.ok().data("list", basicDictService.list(queryWrapper));
    }
}
