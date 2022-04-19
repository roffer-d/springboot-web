package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicRole;
import com.roffer.web.modules.sys.mapper.BasicRoleMapper;
import com.roffer.web.modules.sys.service.BasicRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicRoleServiceImpl extends ServiceImpl<BasicRoleMapper,BasicRole> implements BasicRoleService {
    @Resource
    private BasicRoleMapper userMapper;
}
