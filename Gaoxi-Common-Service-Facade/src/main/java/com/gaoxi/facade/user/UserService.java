package com.gaoxi.facade.user;

import com.gaoxi.entity.user.*;
import com.gaoxi.req.user.*;

import java.util.List;


public interface UserService {

    public UserEntity login(LoginReq loginReq);

    public List<UserEntity> findUsers(UserQueryReq userQueryReq);
}
