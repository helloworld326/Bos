package com.fly.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fly.bos.dao.IRegionDao;
import com.fly.bos.entity.Region;

@SuppressWarnings("all")
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	// 根据关键字进行模糊查询，只要符合Region任何相关字段都会被查找到
	@Override
	public List<Region> findByQ(String q) {
		String hql = "from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.city like ? or r.district like ? ";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
		return list;
	}
	
}		
