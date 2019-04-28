package com.sxx.sivir.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sxx.sivir.core.common.enums.UserPermissionEnum;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.UserManager;
import com.sxx.sivir.core.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 登录逻辑
 */

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    private UserManager userManager;

    @Override
    public User checkLogin(User user) {
        if (null == user.getPhone() || user.getPhone().isEmpty()
                || null == user.getPassword() || user.getPassword().isEmpty()) {
            return null;
        }
        return userManager.selectOne(user);
    }

    @Override
    public Integer registerUser(User user) {
        if (user.getPermission().equals(UserPermissionEnum.TRANS.getCode())) {
            user.setTransRegionId(1L);
        }
        return userManager.insert(user);
    }

    @Override
    public boolean checkRegister(String phone) {
        return null == userManager.selectOne(new EntityWrapper<User>().eq("phone", phone));
    }

}
