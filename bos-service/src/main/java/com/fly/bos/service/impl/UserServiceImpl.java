package com.fly.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IUserDao;
import com.fly.bos.entity.Role;
import com.fly.bos.entity.User;
import com.fly.bos.service.IUseService;
import com.fly.bos.utils.MD5Utils;
import com.fly.bos.utils.PageBean;

@SuppressWarnings("all")
@Service
@Transactional
public class UserServiceImpl implements IUseService {
	@Autowired
	private IUserDao userDao;
	
	
	@Override
	public User login(User t) {
		String password = MD5Utils.getPassword(t.getPassword());
		return userDao.findByUnamePwd(t.getUsername(), password);
	}


	@Override
	public void edit(String id, String password) {
		String pwd = MD5Utils.getPassword(password);
		userDao.executeUpdate("user.editPassword", pwd, id);
	}


	@Override
	public void save(User user, String[] ids) {
		String password = MD5Utils.getPassword(user.getPassword());
		user.setPassword(password);
		userDao.save(user);
		for (String id : ids) {
			Role role = new Role(id);
			user.getRoles().add(role);
		}
	}


	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

	@Override
	public void batchDel(String ids) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			User user = userDao.findById(id);
			user.getRoles().clear();
			userDao.delete(user);
		}
	}

}
