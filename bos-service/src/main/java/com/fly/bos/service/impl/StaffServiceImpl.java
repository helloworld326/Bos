package com.fly.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IStaffDao;
import com.fly.bos.entity.Staff;
import com.fly.bos.service.IStaffService;
import com.fly.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void add(Staff t) {
		staffDao.save(t);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void batchDel(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArr = ids.split(",");
			for (String id : idsArr) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}
	}

	@Override
	public void edit(Staff t) {
		staffDao.update(t);
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public List<Staff> findNotDel() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findByCriteria(criteria);
	}

}
