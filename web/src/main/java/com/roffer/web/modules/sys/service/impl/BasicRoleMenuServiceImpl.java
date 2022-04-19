package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;
import com.roffer.web.modules.sys.mapper.BasicRoleMenuMapper;
import com.roffer.web.modules.sys.service.BasicRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicRoleMenuServiceImpl extends ServiceImpl<BasicRoleMenuMapper,BasicRoleMenu> implements BasicRoleMenuService {
    @Resource
    private BasicRoleMenuMapper userMapper;
}
