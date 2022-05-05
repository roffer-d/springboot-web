package com.roffer.web.modules.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roffer.web.modules.sys.entity.BasicRole;
import com.roffer.web.modules.sys.entity.BasicRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * @author roffer
 */
public interface BasicRoleService extends IService<BasicRole> {
    /**
      * @description 获取角色拥有的权限
      * @params:
      *   roleId(String): 角色id
      * @author Roffer
      * @date 2022/4/29 13:50
      */
    List<BasicRoleMenu> getRoleAuth(String roleId);

    /**
      * @description 保存角色权限
      * @params:
      *   auth(JSONObject): 权限数据
      * @author Roffer
      * @date 2022/4/29 13:50
      */
    void saveRoleAuth(JSONObject auth);

    /**
      * @description 批量删除角色以及角色关联的菜单
      * @params:
      *   ids(String): 要删除的id，多个逗号隔开
      * @author Roffer
      * @date 2022/5/3 18:08
      */
    void removeRoleAndMenuByIds(String ids);
}

