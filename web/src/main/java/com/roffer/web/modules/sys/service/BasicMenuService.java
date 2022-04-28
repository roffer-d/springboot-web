package com.roffer.web.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roffer.web.modules.sys.entity.BasicMenu;

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
    public List<Map<String,Object>> tree(QueryWrapper query);
}

