package com.fly.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IFunctionDao;
import com.fly.bos.entity.Function;
import com.fly.bos.entity.User;
import com.fly.bos.service.IFunctionService;
import com.fly.bos.utils.BOSUtils;
import com.fly.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Autowired
	private IFunctionDao functionDao;
	
	@Override
	public List<Function> findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
		List<Function> list = functionDao.findByCriteria(criteria);
		return list;
	}

	@Override
	public void save(Function t) {
		if(t != null && "".equals(t.getParentFunction().getId())) {
			t.setParentFunction(null);
		}
		functionDao.save(t);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findMenu() {
		User user = BOSUtils.getLoginUser();
		List<Function> list = null;
		if ("admin".equals(user.getUsername())) {
			// 超级管理员用户
			list = functionDao.findAllMenu();
		} else {
			// 其他用户
			list = functionDao.findMenuById(user.getId());
		}
		return list;
	}

}
