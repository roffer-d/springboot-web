package com.roffer.common.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roffer
 * @description 树形
 * @date 2022/4/25 15:29
 */
@Data
public class TreeNode {
    private String id;
    private String pid;
    private String name;
    private Map<String,Object> meta = new HashMap<>();
    private List<TreeNode> children = new ArrayList<>();
    public TreeNode(String id, String pid) {
        this.id = id;
        this.pid = pid;
    }

    public TreeNode(String id, String pid, String name) {
        this(id, pid);
        this.name = name;
    }
}
