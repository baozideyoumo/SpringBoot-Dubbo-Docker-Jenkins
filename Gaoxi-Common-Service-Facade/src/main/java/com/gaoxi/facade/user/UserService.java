package com.gaoxi.facade.user;

import com.gaoxi.entity.user.*;
import com.gaoxi.req.user.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserEntity login(LoginReq loginReq);

    public List<UserEntity> findUsers(UserQueryReq userQueryReq);
}
