package com.roffer.web.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roffer.common.http.ConstEnum;
import com.roffer.common.http.R;
import com.roffer.common.utils.TokenUtil;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.service.BasicUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Dulongfei
 * @description 登录
 * @date 2022/4/18 16:35
 */
@RestController
@Api("登录")
public class LoginController {

    @Resource
    private BasicUserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Object login(@RequestParam String account,@RequestParam String password){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",account);
        wrapper.eq("password",password);
        wrapper.eq("status","1");
        List<BasicUser> userList = userService.list(wrapper);
        if(userList.isEmpty()){
            return R.customError(ConstEnum.NO_USER.getCode(),ConstEnum.NO_USER.getMsg());
        }else{
            BasicUser user = userList.get(0);
            String token = TokenUtil.generator(user.getId());
            return R.ok().data("token",token).data("user",user);
        }
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logOut")
    public Object logOut(){
        return R.ok();
    }
}
