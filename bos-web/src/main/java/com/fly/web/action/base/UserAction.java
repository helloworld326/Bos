package com.fly.web.action.base;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.User;
import com.fly.bos.service.IUseService;
import com.fly.bos.utils.BOSUtils;

// 默认struts配置文件中class属性为该类型首字母小写
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	@Autowired
	private IUseService userService;

	private String checkcode;

	// 表单数据无法提交上来 ??
	private static final long serialVersionUID = 1L;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// 后台针对前台的账户密码如何校验 ??
	/**
	 * 用户登陆功能
	 * 
	 * @return
	 */
	public String login() {
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(code) && code.equals(checkcode)) {
			// 验证码输入正确
			User user = userService.login(t);
			if (user != null) {
				// 登陆成功
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			} else { // 用户名或者密码错误
				this.addActionError("用户名或密码错误，请重新输入");
				return LOGIN;
			}
		} else {
			// 验证码不正确;
			this.addActionError("输入的验证码错误，请重新输入");
			return LOGIN;
		}
	}

	/**
	 * 用户退出功能
	 * 
	 * @return
	 */
	public String logout() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user != null) {
			ServletActionContext.getRequest().getSession().invalidate();
			return LOGIN;
		}
		this.addActionError("抱歉,你还为登陆，不能退出，您可以先登陆");
		return LOGIN;
	}

	/**
	 * 用户密码更改功能
	 * 
	 * @return
	 */
	public String editPassword() {
		String f = "1";
		User user = BOSUtils.getLoginUser();
		try {
			userService.editPassword(user.getId(), t.getPassword());
		} catch (Exception e) {
			f = "0";
			e.printStackTrace();
		}
		try {
			// 告知浏览器返回的是一个字符串而不是一个对象
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().print(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
