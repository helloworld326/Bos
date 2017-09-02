package com.fly.bos.dao;

import java.util.List;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.entity.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	List<Function> findFunctionByUid(String id);

	List<Function> findMenuById(String id);

	List<Function> findAllMenu();

}
