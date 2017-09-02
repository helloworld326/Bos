package com.fly.bos.service;

import java.util.List;

import com.fly.bos.entity.Decidedzone;
import com.fly.bos.utils.PageBean;

public interface IDecidedzoneService {

	void save(Decidedzone t, String[] subarea_ids);

	void pageQuery(PageBean pageBean);

	
}
