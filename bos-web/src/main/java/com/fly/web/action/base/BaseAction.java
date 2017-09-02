package com.fly.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("all")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	public static final java.lang.String HOME = "home";
	public static final java.lang.String LIST = "list";
	
	// 模型对象
	protected T t;

	// 动态获取实体类型，通过反射创建model对象
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];
		// 通过反射来创建来对象
		try {
			t = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return t;
	}

}
