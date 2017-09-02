package com.fly.bos.service;

import com.fly.bos.entity.User;
import com.fly.bos.utils.PageBean;

public interface IUseService {

	User login(User t);

	void edit(String id, String password);

	void save(User user, String[] ids);

	void pageQuery(PageBean pageBean);

	void batchDel(String ids);

}
