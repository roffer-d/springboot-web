package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.mapper.BasicUserMapper;
import com.roffer.web.modules.sys.service.BasicUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicUserServiceImpl extends ServiceImpl<BasicUserMapper,BasicUser> implements BasicUserService {
    @Resource
    private BasicUserMapper userMapper;
}
