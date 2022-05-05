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

    List<BasicUserRole> userRole(String userId);

    void removeUserAndRole(String id);
}

