package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.common.utils.TreeUtils;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.mapper.BasicMenuMapper;
import com.roffer.web.modules.sys.mapper.BasicRoleMenuMapper;
import com.roffer.web.modules.sys.service.BasicMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author roffer
 */
@Service
public class BasicMenuServiceImpl extends ServiceImpl<BasicMenuMapper,BasicMenu> implements BasicMenuService {
    @Resource
    private BasicMenuMapper menuMapper;

    @Resource
    private BasicRoleMenuMapper roleMenuMapper;

    @Override
    /**
     * @description 树形菜单
     * @author Roffer
     * @date 2022/4/25 15:35
     */
    public List<Map<String,Object>> tree(QueryWrapper query){
        List<BasicMenu> menuList = menuMapper.selectList(query);
        return TreeUtils.buildTree(menuList,"id","0","pid","children");
    }

    @Override
    /**
     * @description 删除菜单以及所有子菜单、角色菜单权限
     * @params:
     *   id(String): 菜单id
     * @author Roffer
     * @date 2022/5/5 11:01
     */
    public void removeMenuAndAuth(String id) {
        /** 查询当前菜单以及所有子菜单 **/
        QueryWrapper<BasicMenu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("id",id).or().like("pids",id);
        List<BasicMenu> menuList = menuMapper.selectList(menuQueryWrapper);

        /** 删除角色关联的菜单 **/
        QueryWrapper<BasicRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.in("menu_id",menuList.stream().map(m->m.getId()).collect(Collectors.toList()));
        roleMenuMapper.delete(roleMenuQueryWrapper);

        /** 删除当前菜单以及所有子菜单 **/
        menuMapper.delete(menuQueryWrapper);
    }

    @Override
    /**
     * @description 获取用户菜单
     * @params:
     *   userId(String): 用户id
     * @author Roffer
     * @date 2022/5/5 14:42
     */
    public List<BasicMenu> getUserMenu(String userId) {
        return menuMapper.getUserMenu(userId);
    }

    @Override
    /**
     * @description 获取角色权限
     * @params:
     *   userId(String): 用户id
     * @author Roffer
     * @date 2022/5/5 16:11
     */
    public List<BasicRoleMenu> getRoleMenu(String userId) {
        return roleMenuMapper.getRoleMenu(userId);
    }
}
