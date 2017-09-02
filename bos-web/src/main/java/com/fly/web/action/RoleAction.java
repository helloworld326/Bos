package com.fly.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Role;
import com.fly.bos.service.IRoleService;

@Controller
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IRoleService roleService;
	
	private String ids;
	
	/**
	 * 添加角色并关联权限
	 * @return
	 */
	public String add() {
		roleService.save(t, ids);
		return LIST;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"functions", "users"});
		return NONE;
	}
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public String findAll(){
		List<Role> list = roleService.findAll();
		java2Json(list, new String[]{"functions", "users"});
		return NONE;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
