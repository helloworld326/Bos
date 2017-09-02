package com.fly.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IRoleDao;
import com.fly.bos.entity.Function;
import com.fly.bos.entity.Role;
import com.fly.bos.service.IRoleService;
import com.fly.bos.utils.PageBean;

@SuppressWarnings("all")
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;

	@Override
	public void save(Role role, String ids) {
		roleDao.save(role);
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				Function function = new Function();
				function.setId(id);
				role.getFunctions().add(function);
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		return roleDao.findByCriteria(criteria);
	}

}
