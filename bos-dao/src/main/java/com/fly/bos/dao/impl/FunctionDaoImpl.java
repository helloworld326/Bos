package com.fly.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fly.bos.dao.IFunctionDao;
import com.fly.bos.entity.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findFunctionByUid(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> list = (List<Function>) getHibernateTemplate().find(hql, id);
		return list;
	}

	@Override
	public List<Function> findAllMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex DESC";
		return (List<Function>) getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Function> findMenuById(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?"
				+ " AND f.generatemenu = '1' ORDER BY f.zindex DESC";
		return (List<Function>) getHibernateTemplate().find(hql, id);
	}

}
