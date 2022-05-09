package com.roffer.web.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicRole;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.entity.BasicUserRole;
import com.roffer.web.modules.sys.mapper.BasicRoleMapper;
import com.roffer.web.modules.sys.mapper.BasicRoleMenuMapper;
import com.roffer.web.modules.sys.mapper.BasicUserRoleMapper;
import com.roffer.web.modules.sys.service.BasicRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author roffer
 */
@Service
public class BasicRoleServiceImpl extends ServiceImpl<BasicRoleMapper,BasicRole> implements BasicRoleService {
    @Resource
    private BasicRoleMapper roleMapper;

    @Resource
    private BasicRoleMenuMapper roleMenuMapper;

    @Resource
    private BasicUserRoleMapper userRoleMapper;

    @Override
    public List<BasicRoleMenu> getRoleAuth(String roleId) {
        QueryWrapper<BasicRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        return roleMenuMapper.selectList(queryWrapper);
    }

    @Override
    public void saveRoleAuth(JSONObject auth) {
        String roleId = auth.getString("roleId");
        JSONArray array = auth.getJSONArray("auth");

        QueryWrapper<BasicRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleMenuMapper.delete(queryWrapper);

        if(!array.isEmpty()){
            array.forEach(item->{
                JSONObject obj = JSONObject.parseObject(JSON.toJSONString(item));
                BasicRoleMenu roleMenu = new BasicRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(obj.getString("menuId"));
                roleMenu.setAuthorityType(obj.getString("authorityType"));

                roleMenuMapper.insert(roleMenu);
            });
        }
    }

    @Override
    public void removeRoleAndMenuByIds(String ids) {
        String[] array = ids.split(",");

        /** 删除角色权限 **/
        for(int i = 0 ; i < array.length ; i ++){
            QueryWrapper<BasicRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
            roleMenuQueryWrapper.eq("role_id",array[i]);
            roleMenuMapper.delete(roleMenuQueryWrapper);
        }

        /** 删除用户角色 **/
        QueryWrapper<BasicUserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.in("role_id",array);
        userRoleMapper.delete(userRoleQueryWrapper);

        /** 删除角色 **/
        roleMapper.deleteBatchIds(Arrays.asList(array));
    }
}
