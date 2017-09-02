package com.fly.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IDecidedzoneDao;
import com.fly.bos.dao.ISubareaDao;
import com.fly.bos.entity.Decidedzone;
import com.fly.bos.entity.Subarea;
import com.fly.bos.service.IDecidedzoneService;
import com.fly.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImp implements IDecidedzoneService {

	@Autowired
	private IDecidedzoneDao decidedDao;
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void save(Decidedzone t, String[] subarea_ids) {
		decidedDao.save(t);
		for (String id : subarea_ids) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(t);
			// 持久态自动更新到数据库
			// subareaDao.save(subarea);
		}

	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedDao.pageQuery(pageBean);
	}
}
