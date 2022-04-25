package com.roffer.web.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roffer.common.utils.TreeNode;
import com.roffer.common.utils.TreeUtil;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.mapper.BasicMenuMapper;
import com.roffer.web.modules.sys.service.BasicMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<TreeNode> menuTree(){
        List<BasicMenu> menuList = menuMapper.selectList(new QueryWrapper<>());
        List<TreeNode> nodeList = new ArrayList<>();
        menuList.forEach(menu->{
            TreeNode treeNode = new TreeNode(menu.getId(),menu.getParentId(),menu.getName());
            Map<String,Object> meta = new HashMap<>();
            meta.put("router",menu.getRouter());
            meta.put("icon",menu.getIcon());
            treeNode.setMeta(meta);
            nodeList.add(treeNode);
        });
        return TreeUtil.buildTree(nodeList);
    }

}
