package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicUserRole;
import com.roffer.web.modules.sys.mapper.BasicUserRoleMapper;
import com.roffer.web.modules.sys.service.BasicUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicUserRoleServiceImpl extends ServiceImpl<BasicUserRoleMapper,BasicUserRole> implements BasicUserRoleService {
    @Resource
    private BasicUserRoleMapper userMapper;
}
