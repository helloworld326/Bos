package com.fly.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fly.bos.entity.User;

/**
 * BOS工具类
 * @author 轻舞飞扬
 * @version
 * @date2017年8月20日
 * 
 */
public class BOSUtils {
	
	/**
	 * 获取HttpSession对象
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	
	/**
	 * 获取已经登陆的用户
	 * @return
	 */
	public static User getLoginUser() {
		return (User) getSession().getAttribute("user");
	}
}
