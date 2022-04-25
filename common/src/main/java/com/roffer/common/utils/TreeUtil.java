package com.roffer.common.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Roffer
 * @description 树形数据工具类
 * @date 2022/4/25 15:32
 */
public class TreeUtil {
    /**
      * @description 树形数据封装
      * @params:
      *   nodes(List): 描述
      * @author Roffer
      * @date 2022/4/25 15:33
      */
    public static List<TreeNode> buildTree(List<TreeNode> nodes) {
        Map<String, List<TreeNode>> sub = nodes.stream().filter(node -> !node.getPid().equals("0")).collect(Collectors.groupingBy(node -> node.getPid()));
        nodes.forEach(node -> node.setChildren(sub.get(node.getId())));
        return nodes.stream().filter(node -> node.getPid().equals("0")).collect(Collectors.toList());
    }
}
