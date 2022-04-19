package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.mapper.BasicMenuMapper;
import com.roffer.web.modules.sys.service.BasicMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author roffer
 */
@Service
public class BasicMenuServiceImpl extends ServiceImpl<BasicMenuMapper,BasicMenu> implements BasicMenuService {
    @Resource
    private BasicMenuMapper userMapper;
}
