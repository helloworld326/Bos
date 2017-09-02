package com.fly.bos.dao;

import java.util.List;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.entity.Region;

public interface IRegionDao extends IBaseDao<Region> {

	List<Region> findByQ(String q);
	
}
