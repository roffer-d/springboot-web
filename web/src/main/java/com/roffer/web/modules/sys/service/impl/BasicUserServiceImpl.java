package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.common.utils.RedisUtils;
import com.roffer.web.exception.BaseException;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.entity.BasicUserRole;
import com.roffer.web.modules.sys.mapper.BasicRoleMenuMapper;
import com.roffer.web.modules.sys.mapper.BasicUserMapper;
import com.roffer.web.modules.sys.mapper.BasicUserRoleMapper;
import com.roffer.web.modules.sys.service.BasicUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author roffer
 */
@Service
public class BasicUserServiceImpl extends ServiceImpl<BasicUserMapper,BasicUser> implements BasicUserService {
    @Resource
    private BasicUserMapper userMapper;

    @Resource
    private BasicUserRoleMapper userRoleMapper;

    @Resource
    private BasicRoleMenuMapper userRoleMenuMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void saveRole(String userId, String roleIds) {
        String[] roles = roleIds.split(",");
        QueryWrapper<BasicUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        userRoleMapper.delete(queryWrapper);

        for(int i = 0 ; i < roles.length ; i ++){
            BasicUserRole userRole = new BasicUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roles[i]);
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public List<BasicUserRole> userRole(String userId) {
        QueryWrapper<BasicUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        return userRoleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void removeUserAndRole(String id) {
        /** 查询用户关联的角色 **/
        QueryWrapper<BasicUserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",id);
        List<BasicUserRole> roleList = userRoleMapper.selectList(userRoleQueryWrapper);

        /** 删除角色关联的权限 **/
        QueryWrapper<BasicRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.in("role_id",roleList.stream().map(r->r.getRoleId()).collect(Collectors.toList()));
        userRoleMenuMapper.delete(roleMenuQueryWrapper);

        /** 删除用户关联的角色 **/
        userRoleMapper.delete(userRoleQueryWrapper);

        /** 删除用户 **/
        userMapper.deleteById(id);
    }
}
