package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Function;
import com.fly.bos.utils.PageBean;

public interface IFunctionService {

	List<Function> findAll();

	void save(Function t);

	void pageQuery(PageBean pageBean);

	List<Function> findMenu();

}
