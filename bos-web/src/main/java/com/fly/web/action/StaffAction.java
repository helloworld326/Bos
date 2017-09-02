package com.fly.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.Staff;
import com.fly.bos.service.IStaffService;
import com.fly.bos.utils.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;

	private int page;
	private int rows;
	
	// 获取要删除的id数据
	private String ids; 
	
	@Autowired
	private IStaffService staffService;
	
	/**
	 * 添加取派员方法
	 * @return
	 */
	public String add() {
		try {
			staffService.add(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}
	
	/**
	 * 取派员分页查询方法
	 * @return
	 */
	public String pageQuery() {
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(rows);
		pageBean.setCurrentPage(page);
		// 告知hibernate查找哪张表
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setCriteria(criteria);
		staffService.pageQuery(pageBean); // 此处pageBean是一个引用，所以传入后形参的改变会影响实参;
		// 将有用的数据 total,rows返回，其他不返回
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"currentPage", "pageSize", "criteria"});
		String pageStr = JSONObject.fromObject(pageBean, config).toString();
		try {
			ServletActionContext.getResponse().getWriter().write(pageStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 取派员批量删除的方法
	 * service层使用遍历循环
	 * @return
	 */
	public String batchDel() {
		staffService.batchDel(ids);
		return LIST;
	}
	
	/**
	 * 修改取派员信息
	 * 需要先根据指定ID查询，而后进行属性覆盖
	 * @return
	 */
	public String edit() { // t对象不能直接进行update，因为修改页面信息不全会把其他信息变成默认值
		// 先查根据id询数据
		System.out.println(t.getId());
		Staff staff = staffService.findById(t.getId());
		staff.setName(t.getName());
		staff.setTelephone(t.getTelephone());
		staff.setStation(t.getStation());
		staff.setStandard(t.getStandard());
		staff.setHaspda(t.getHaspda());
		staffService.edit(staff);
		return LIST;
	}
	
	/**
	 * 查询为删除的取派员
	 * @return
	 */
	public String list() {
		List<Staff> list = staffService.findNotDel();
		java2Json(list, new String[]{"decidedzones"});
		return NONE;
	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public int getRows() {
		return rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	
	
}
