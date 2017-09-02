package com.fly.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.bos.entity.User;
import com.fly.bos.service.IUseService;
import com.fly.bos.utils.BOSUtils;
import com.fly.bos.utils.MD5Utils;

// 默认struts配置文件中class属性为该类型首字母小写
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	@Autowired
	private IUseService userService;

	private String checkcode;

	private String[] roleIds;
	
	private String ids;
	
	// 表单数据无法提交上来 ??
	private static final long serialVersionUID = 1L;

	// 后台针对前台的账户密码如何校验 ??
	/**
	 * 用户登录
	 * @return
	 */
	public String login() {
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(code) && code.equals(checkcode)) {
			// 验证码输入正确
			
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(t.getUsername(), MD5Utils.getPassword(t.getPassword()));
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return LOGIN;
			}
			
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return HOME;
		} else {
			// 验证码不正确;
			this.addActionError("输入的验证码错误,请重新输入");
			return LOGIN;
		}
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String logout() {
		User user = BOSUtils.getLoginUser();
		if (user != null) {
			ServletActionContext.getRequest().getSession().invalidate();
			return LOGIN;
		}
		this.addActionError("抱歉，你还为登陆，不能退出，您可以先登陆");
		return LOGIN;
	}

	/**
	 * 密码修改功能
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException {
		User user = BOSUtils.getLoginUser();
		String f = "1";
		try {
			userService.edit(user.getId(), t.getPassword());
		} catch (Exception e) {
			f = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().getWriter().write(f);
		return NONE; // 要回写信息必须返回None
	}
	
	/**
	 * 用户添加角色功能
	 * @return
	 */
	public String add() {
		System.out.println(roleIds);
		userService.save(t, roleIds);
		return LIST;
	}
	
	/**
	 * 用户管理分页查询
	 * @return
	 */
	public String pageQuery() {
		userService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"password", "noticebills", "roles", "remark"});
		return NONE;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws IOException 
	 */
	public String batchDel() throws IOException {
		System.out.println(ids);
		String f = "1"; // 用于告知页面是否删除成功
		try {
			userService.batchDel(ids);
		} catch (Exception e) {
			f = "0";
			e.printStackTrace();
		}
		
		ServletActionContext.getResponse().getWriter().write(f);;
		return NONE;
	}
	
	public String login_bak() {
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(code) && code.equals(checkcode)) {
			// 验证码输入正确
			User user = userService.login(t);
			if (user != null) {
				// 登陆成功
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			} else { // 用户名或者密码错误
				this.addActionError("用户名或密码错误,请重新输入");
				return LOGIN;
			}
		} else {
			// 验证码不正确;
			this.addActionError("输入的验证码错误,请重新输入");
			return LOGIN;
		}
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
}
