package com.roffer.web.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roffer.web.modules.sys.entity.BasicMenu;

import java.util.List;

/**
 * @author roffer
 */
public interface BasicMenuMapper extends BaseMapper<BasicMenu> {

    /**
     * @description 获取用户菜单
     * @params:
     *   userId(String): 用户id
     * @author Roffer
     * @date 2022/5/5 14:42
     */
    List<BasicMenu> getUserMenu(String userId);
}

