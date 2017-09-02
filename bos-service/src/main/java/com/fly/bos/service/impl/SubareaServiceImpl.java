package com.fly.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.ISubareaDao;
import com.fly.bos.entity.Subarea;
import com.fly.bos.service.ISubareaService;
import com.fly.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void add(Subarea t) {
		subareaDao.save(t);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll(Subarea t) {
		return subareaDao.findAll(t);
	}

	@Override
	public List<Subarea> findNotJoinArea() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(criteria );
	}

	@Override
	public List<Subarea> findByDecidedId(String decidedzone_id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		// QBC查询要添加对应java bean的字段名
		criteria.add(Restrictions.eq("decidedzone.id", decidedzone_id));
		List<Subarea> list = subareaDao.findByCriteria(criteria);
		return list;
	}

	@Override
	public List<Object> findSubareasGroupByProvince() {
		return subareaDao.findSubareasGroupByProvince();
	}

}
