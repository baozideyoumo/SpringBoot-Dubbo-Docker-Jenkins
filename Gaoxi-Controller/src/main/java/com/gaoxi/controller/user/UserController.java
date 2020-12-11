package com.gaoxi.controller.user;

import com.gaoxi.annotation.Login;
import com.gaoxi.req.user.*;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:26
 */
@RestController
public interface UserController {

    /**
     * 登录
     * @param loginReq 登录请求参数
     * @param httpRsp HTTP响应
     * @return 登录是否成功
     */
    @GetMapping("/login")
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp);

    /**
     * 登出
     * @param httpReq HTTP请求
     * @param httpRsp HTTP响应
     * @return 是否登出成功
     */
    @GetMapping("/logout")
    @Login
    public Result logout(HttpServletRequest httpReq, HttpServletResponse httpRsp);


}
