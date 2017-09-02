package com.fly.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fly.bos.dao.ISubareaDao;
import com.fly.bos.entity.Subarea;

@SuppressWarnings("all")
@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {

	@Override
	public List<Object> findSubareasGroupByProvince() {
		String hql = "SELECT r.province ,count(*) FROM Subarea s LEFT OUTER JOIN s.region r Group BY r.province";
		return (List<Object>) getHibernateTemplate().find(hql);
	}


}
