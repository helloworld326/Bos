package com.fly.bos.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.fly.bos.dao.IWorkbillDao;
import com.fly.bos.entity.Workbill;

@Repository
public class WorkbillDaoImpl extends BaseDaoImpl<Workbill> implements IWorkbillDao {

	@Override
	public List<Workbill> findNewWorkbills() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Workbill.class);
		List<Workbill> list = (List<Workbill>) getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
