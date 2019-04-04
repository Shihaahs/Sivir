package com.sxx.sivir.core.dal.manager.impl;

import com.sxx.sivir.core.common.base.BaseManagerImpl;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.dao.UserDao;
import com.sxx.sivir.core.dal.manager.UserManager;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl extends BaseManagerImpl<UserDao, User> implements UserManager{

}
