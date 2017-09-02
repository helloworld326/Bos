package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Staff;
import com.fly.bos.utils.PageBean;

public interface IStaffService {

	void add(Staff t);

	void pageQuery(PageBean pageBean);

	void batchDel(String ids);

	void edit(Staff t);

	Staff findById(String id);

	List<Staff> findNotDel();

}
