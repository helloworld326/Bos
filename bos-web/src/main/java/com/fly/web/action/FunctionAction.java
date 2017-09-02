package com.fly.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Function;
import com.fly.bos.service.IFunctionService;

/**
 * 权限管理功能模块
 * @author 轻舞飞扬
 * @version
 * @date2017年8月31日
 * 
 */
@Controller
public class FunctionAction extends BaseAction<Function> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IFunctionService  functionService;
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public String findAll(){
		List<Function> list = functionService.findAll();
		java2Json(list, new String[]{"parentFunction", "roles"});
		System.out.println(list);
		return NONE;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String add() {
		functionService.save(t);
		return LIST;
	}
	
	/**
	 * 分页查询功权限表
	 * @return
	 */
	public String pageQuery() {
		String page = t.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"parentFunction", "functions", "roles"});
		return NONE;
	}
	
	/**
	 * 根据用户查找菜单
	 * @return
	 */
	public String findMenu(){
		List<Function> list = functionService.findMenu();
		java2Json(list, new String[]{"parentFunction", "functions", "roles"});
		return NONE;
	}

}
