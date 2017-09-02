package com.fly.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fly.bos.dao.IUserDao;
import com.fly.bos.entity.User;

@SuppressWarnings("all")
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUnamePwd(String username, String password) {
		String hql = "from User u where u.username = ? and u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
		if (list != null && list.size() > 0) {
			// 查找成功
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		String hql = "from User u where u.username = ? ";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			// 查找成功
			return list.get(0);
		}
		return null;
	}

}
