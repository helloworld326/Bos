package com.fly.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.bos.dao.IRegionDao;
import com.fly.bos.entity.Region;
import com.fly.bos.service.IRegionService;
import com.fly.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public void add(Region t) {
		regionDao.saveOrUpdate(t);
	}

	@Override
	public void delete(Region t) {
		regionDao.delete(t);
	}

	@Override
	public void edit(Region t) {
		regionDao.save(t);
	}

	@Override
	public List<Region> findAll(Region region) {
		List<Region> regionList = regionDao.findAll(region);
		return regionList;
	}

	@Override
	public List<Region> findByQ(String q) {
		List<Region> list = (List<Region>) regionDao.findByQ(q);
		return list;
	}

}
