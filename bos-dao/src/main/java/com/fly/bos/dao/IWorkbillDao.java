package com.fly.bos.dao;

import java.util.List;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.entity.Workbill;

public interface IWorkbillDao extends IBaseDao<Workbill> {

	List<Workbill> findNewWorkbills();
	
}
