package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.entity.BasicUserRole;
import com.roffer.web.modules.sys.mapper.BasicUserMapper;
import com.roffer.web.modules.sys.mapper.BasicUserRoleMapper;
import com.roffer.web.modules.sys.service.BasicUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author roffer
 */
@Service
public class BasicUserServiceImpl extends ServiceImpl<BasicUserMapper,BasicUser> implements BasicUserService {
    @Resource
    private BasicUserMapper userMapper;

    @Resource
    private BasicUserRoleMapper userRoleMapper;

    @Override
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
}
