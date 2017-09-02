package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Subarea;
import com.fly.bos.utils.PageBean;

public interface ISubareaService {

	void add(Subarea t);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll(Subarea t);

	List<Subarea> findNotJoinArea();

	List<Subarea> findByDecidedId(String decidedzone_id);

	List<Object> findSubareasGroupByProvince();

	
}
