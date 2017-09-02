package com.fly.web.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.fly.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("all")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected PageBean pageBean = new PageBean();
	private DetachedCriteria criteria = null;
	
	public static final java.lang.String HOME = "home";
	public static final java.lang.String LIST = "list";
	
	// 模型对象
	protected T t;

	// 动态获取实体类型，通过反射创建model对象
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		criteria = criteria.forClass(entityClass);
		pageBean.setCriteria(criteria);
		
		// 通过反射来创建来对象
		try {
			t = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将有用的分页数据返回页面进行显示
	 * @param o
	 * @param excludes
	 */
	public void java2Json(Object o, String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String json = JSONObject.fromObject(o, config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于添加修改窗口区域设置中的下拉框
	 * @param list 
	 * @param excludes 去除不需要回传的数据
	 */
	public void java2Json(List list, String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String pageStr = JSONArray.fromObject(list, config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().write(pageStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return t;
	}
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

}
