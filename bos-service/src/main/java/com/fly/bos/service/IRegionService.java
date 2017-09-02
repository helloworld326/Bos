package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Region;
import com.fly.bos.utils.PageBean;

public interface IRegionService {

	void saveBatch(List<Region> regionList);

	void pageQuery(PageBean pageBean);

	void add(Region t);

	void delete(Region t);

	void edit(Region t);

	List<Region> findAll(Region t);

	List<Region> findByQ(String q);
	
}
