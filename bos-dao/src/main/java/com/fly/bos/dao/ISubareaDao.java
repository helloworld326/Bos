package com.fly.bos.dao;

import java.util.List;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.entity.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

	List<Object> findSubareasGroupByProvince();
	
}
