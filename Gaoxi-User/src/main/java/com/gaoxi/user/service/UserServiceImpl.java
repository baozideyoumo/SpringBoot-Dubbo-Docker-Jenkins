package com.gaoxi.user.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.user.*;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.user.*;
import com.gaoxi.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @description 用户相关操作
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /** 用户表主键的前缀 */
    private static final String USER_KEY_PREFIX = "USER";

    @Override
    public UserEntity login(LoginReq loginReq) {

        // 校验参数
        checkParam(loginReq);

        // 创建用户查询请求
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userDAO.findUsers(userQueryReq);

        // 查询失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new CommonBizException(ExpCodeEnum.LOGIN_FAIL);
        }

        // 查询成功
        return userEntityList.get(0);
    }

    @Override
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userDAO.findUsers(userQueryReq);
    }

    /**
     * 构造用户查询对象
     * @param loginReq 登录请求
     * @return 用户查询请求
     */
    private UserQueryReq buildUserQueryReq(LoginReq loginReq) {
        UserQueryReq userQueryReq = new UserQueryReq();
        // 设置密码
        userQueryReq.setPassword(loginReq.getPassword());
        // 设置用户名
        if (StringUtils.isNotEmpty(loginReq.getUsername())) {
            userQueryReq.setUsername(loginReq.getUsername());
        }
        // 设置邮箱
        if (StringUtils.isNotEmpty(loginReq.getMail())) {
            userQueryReq.setMail(loginReq.getMail());
        }
        // 设置手机号
        if (StringUtils.isNotEmpty(loginReq.getPhone())) {
            userQueryReq.setPhone(loginReq.getPhone());
        }

        return userQueryReq;
    }

    /**
     * 参数校验
     * @param loginReq
     */
    private void checkParam(LoginReq loginReq) {
        // 密码不能为空
        if (StringUtils.isEmpty(loginReq.getPassword())) {
            throw new CommonBizException(ExpCodeEnum.PASSWD_NULL);
        }

        // 手机、mail、用户名 至少填一个
        if (StringUtils.isEmpty(loginReq.getUsername()) &&
                StringUtils.isEmpty(loginReq.getMail()) &&
                StringUtils.isEmpty(loginReq.getPhone())) {
            throw new CommonBizException(ExpCodeEnum.AUTH_NULL);
        }
    }
}
