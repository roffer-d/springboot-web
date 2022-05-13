package com.roffer.web.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.entity.BasicUserRole;

import java.util.List;

/**
 * @author roffer
 */
public interface BasicUserService extends IService<BasicUser> {
    void saveRole(String userId,String roleIds);

    /**
      * @description 方法描述
      * @param userId 用户id
      * @author Roffer
      * @date 2022/5/13 18:01
      */
    List<BasicUserRole> userRole(String userId);

    /**
      * @description 删除用户、用户角色
      * @param id 用户id
      * @author Roffer
      * @date 2022/5/13 18:01
      */
    void removeUserAndRole(String id);
}

