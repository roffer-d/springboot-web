package com.roffer.web.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;

import java.util.List;

/**
 * @author roffer
 */
public interface BasicRoleMenuMapper extends BaseMapper<BasicRoleMenu> {
    /**
     * @description 获取角色权限
     * @params:
     *   userId(String): 用户id
     * @author Roffer
     * @date 2022/5/5 16:11
     */
    List<BasicRoleMenu> getRoleMenu(String userId);
}

