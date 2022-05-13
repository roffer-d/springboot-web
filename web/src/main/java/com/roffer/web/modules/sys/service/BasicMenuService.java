package com.roffer.web.modules.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * @author roffer
 */
public interface BasicMenuService extends IService<BasicMenu> {
    /**
     * @description 树形菜单
     * @author Roffer
     * @date 2022/4/25 15:35
     */
    List<Map<String,Object>> tree(QueryWrapper query);

    /**
      * @description 删除菜单以及所有子菜单、角色菜单权限
      * @params:
      *   id(String): 菜单id
      * @author Roffer
      * @date 2022/5/5 11:01
      */
    void removeMenuAndAuth(String id);

    void menuAuth(JSONObject auth);

    /**
     * @description 获取用户角色、角色权限
     * @params:
     *   userId(String): 用户id
     * @author Roffer
     * @date 2022/5/5 16:11
     */
    Map<String,Object> getAuth(String userId);
}

