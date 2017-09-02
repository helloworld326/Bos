package com.fly.bos.dao;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.entity.User;

public interface IUserDao extends IBaseDao<User> {

	User findByUnamePwd(String username, String password);

	User findUserByUsername(String username);

}
