package com.roffer.web.modules.sys.controller;

import com.roffer.common.utils.BeanUtils;
import com.roffer.common.utils.TokenUtils;
import com.roffer.common.utils.TreeUtils;
import com.roffer.web.modules.sys.entity.BasicRole;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.service.BasicMenuService;
import com.roffer.web.modules.sys.entity.BasicMenu;

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
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/basicMenu")
/**
 * @description 菜单
 * @author roffer
 * @date 2022-04-25
 */
@Api("菜单相关")
public class BasicMenuController {
    /** 根菜单 **/
    private String ROOT_MENU_ID = "1518483664025964545";

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
        QueryWrapper<BasicMenu> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");
        return R.ok().data("list", basicMenuService.list());
    }

    @ApiOperation(value = "分页菜单")
    @PostMapping("/listPage")
    public Object listPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String router,
            @RequestParam(required = false) String icon,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize) {

        Page<BasicMenu> basicMenuPage = null;
        if(null == pageNum && null == pageSize){
            basicMenuPage = new Page<>();
        }else{
            basicMenuPage = new Page<>(pageNum, pageSize);
        }

        QueryWrapper<BasicMenu> queryWrapper = new QueryWrapper();
        Boolean isSearch = false;
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
            isSearch = true;
        }
        if (StringUtils.isNotBlank(router)) {
            queryWrapper.like("router", router);
            isSearch = true;
        }
        if (StringUtils.isNotBlank(icon)) {
            queryWrapper.like("icon", icon);
            isSearch = true;
        }
        if (StringUtils.isNotBlank(remark)) {
            queryWrapper.like("remark", remark);
            isSearch = true;
        }

        if(!isSearch){
            /** 查询根菜单下的直属菜单 **/
            queryWrapper.eq("pid",ROOT_MENU_ID);
        }

        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("update_time");

        basicMenuService.page(basicMenuPage,queryWrapper);

        long total = basicMenuPage.getTotal();
        List<BasicMenu> basicMenuList = basicMenuPage.getRecords();

        /** 获取菜单下的所有子菜单，组装成一棵树 **/
        List<Object> resultList = getChildrenMenu(basicMenuList);

        return R.ok().data("total", total).data("list", resultList);
    }

    @ApiOperation(value = "获取用户权限")
    @PostMapping("/getAuth")
    public Object getAuth(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userId = TokenUtils.getIdFromToken(token);

        /** 获取用户拥有的菜单 **/
        List<BasicMenu> menuList = basicMenuService.getUserMenu(userId);
        List<Map<String,Object>> resultList = TreeUtils.buildTree(menuList,"id",ROOT_MENU_ID,"pid","children");

        /** 获取用户拥有的角色权限 **/
        List<BasicRoleMenu> roleMenuList = basicMenuService.getRoleMenu(userId);

        return R.ok().data("menuList",resultList).data("roleMenuList",roleMenuList);
    }

    @ApiOperation(value = "菜单树")
    @PostMapping("/menuTree")
    public Object menuTree() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("create_time");
        wrapper.orderByDesc("update_time");
        List<Map<String,Object>> menuTree = basicMenuService.tree(wrapper);
        return R.ok().data("list",menuTree);
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
        basicMenuService.removeMenuAndAuth(id);
        return R.ok();
    }

    @ApiOperation(value = "批量删除菜单")
    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestParam String ids) {
        basicMenuService.removeByIds(Arrays.asList(ids.split(",")));
        return R.ok();
    }

    /**
      * @description 获取菜单下的所有子菜单，组装成一棵树
      * @params:
      *   menuList(List): 菜单列表
      * @author Roffer
      * @date 2022/5/5 14:29
      */
    private List getChildrenMenu(List<BasicMenu> menuList){
        String childrenKey = "children";
        return menuList.stream().map(menu -> {
            /** 查询当前菜单下的所有子菜单 **/
            QueryWrapper query = new QueryWrapper();
            query.like("pids",menu.getId());
            List<BasicMenu> childrenList = basicMenuService.list(query);

            /** 将当前菜单下的所有子菜单封装成树形菜单 **/
            List<Map<String, Object>> listMap = TreeUtils.buildTree(childrenList, "id", menu.getId(), "pid", childrenKey);

            /** 动态生成子节点数据，覆盖当前遍历对象menu（生成了一个新的Object，非当前menu） **/
            Map<String, Object> addProperties = new HashMap<>();
            addProperties.put(childrenKey, listMap);
            return BeanUtils.getTarget(menu, addProperties);
        }).collect(Collectors.toList());
    }
}
