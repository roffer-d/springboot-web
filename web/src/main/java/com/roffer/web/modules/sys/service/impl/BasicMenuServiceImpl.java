package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.common.utils.TreeUtils;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.mapper.BasicMenuMapper;
import com.roffer.web.modules.sys.service.BasicMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author roffer
 */
@Service
public class BasicMenuServiceImpl extends ServiceImpl<BasicMenuMapper,BasicMenu> implements BasicMenuService {
    @Resource
    private BasicMenuMapper menuMapper;

    @Override
    public List<Map<String,Object>> tree(QueryWrapper query){
        List<BasicMenu> menuList = menuMapper.selectList(query);
        return TreeUtils.buildTree(menuList,"id","0","pid","children");
    }
}
