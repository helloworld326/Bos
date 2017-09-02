package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Role;
import com.fly.bos.utils.PageBean;

public interface IRoleService {

	void save(Role role, String ids);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();
	
}
