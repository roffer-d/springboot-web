package com.roffer.web.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.roffer.common.http.ConstEnum;
import com.roffer.common.http.R;
import com.roffer.common.utils.RedisUtils;
import com.roffer.common.utils.TokenUtils;
import com.roffer.common.utils.TreeUtils;
import com.roffer.web.modules.sys.entity.BasicMenu;
import com.roffer.web.modules.sys.entity.BasicUser;
import com.roffer.web.modules.sys.service.BasicMenuService;
import com.roffer.web.modules.sys.service.BasicUserService;
import com.roffer.web.enums.RedisConstEnum;
import com.roffer.web.utils.VerifyImageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Dulongfei
 * @description 登录
 * @date 2022/4/18 16:35
 */
@RestController
@Api("登录")
public class LoginController {

    /**
     * 根菜单
     **/
    private String ROOT_MENU_ID = "1518483664025964545";

    @Resource
    private BasicUserService userService;

    @Resource
    private BasicMenuService basicMenuService;

    @Value("${captcha.template-image}")
    private String templatePath;

    @Value("${captcha.target-image}")
    private String targetPath;

    @Resource
    private RedisUtils redisUtils;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Object login(@RequestParam String account, @RequestParam String password) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account", account);
        wrapper.eq("password", password);
        wrapper.eq("status", "1");
        List<BasicUser> userList = userService.list(wrapper);
        if (userList.isEmpty()) {
            return R.customError(ConstEnum.NO_USER.getCode(), ConstEnum.NO_USER.getMsg());
        } else {
            BasicUser user = userList.get(0);
            user.setLastLoginTime(new Date());

            String token = TokenUtils.generator(user.getId());
            Map<String,Object> authMap = basicMenuService.getAuth(user.getId());

            /** 登录信息存入redis，有效期1天 **/
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("token",token);
            userMap.put("user",user);
            userMap.put("menu",authMap.get("menu"));
            userMap.put("role",authMap.get("role"));
            redisUtils.set(RedisConstEnum.USER.getValue() + user.getId(), userMap, RedisConstEnum.USER.getTtl());

            /** 更新最后登录时间 **/
            userService.updateById(user);

            return R.ok()
                    .data("token", token)
                    .data("user", user)
                    .data("menu", authMap.get("menu"))
                    .data("role", authMap.get("role"));
        }
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logOut")
    public Object logOut(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userId = TokenUtils.getIdFromToken(token);

        redisUtils.del(RedisConstEnum.USER.getValue() + userId);

        return R.ok();
    }

    @ApiOperation(value = "生成滑块图片")
    @PostMapping("/createImg")
    public Object createImg() {
        Map<String, Object> resultMap = new HashMap<>();
        String cacheKey = RedisConstEnum.LOGIN_IMAGE_INFO.getValue() + System.currentTimeMillis();
        try {
            Integer targetNum = new Random().nextInt(4) + 1;

            File templateFile = ResourceUtils.getFile("classpath:" + templatePath);
            File targetFile = ResourceUtils.getFile("classpath:" + targetPath + "/" + targetNum + ".png");
            resultMap = VerifyImageUtils.pictureTemplatesCut(templateFile, targetFile, "PNG", "PNG");
            //缓存5分钟
            redisUtils.set(cacheKey, resultMap, 5 * 60);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultMap.put("cacheKey", cacheKey);
        return R.ok().data(resultMap);
    }

    @ApiOperation(value = "验证滑块图片")
    @PostMapping("/checkImg")
    public Object checkImg(@RequestParam String cacheKey, @RequestParam int left) {
        Boolean result = false;
        Map<String, Object> map = redisUtils.get(cacheKey, Map.class);
        if (!map.isEmpty()) {
            int x = Integer.parseInt(String.valueOf(map.get("x")));
            result = left > x - 5 && left < x + 5;

            redisUtils.del(cacheKey);
        }
        return R.ok().data("valid", result);
    }
}
