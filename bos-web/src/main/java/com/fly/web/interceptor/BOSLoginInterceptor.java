package com.fly.web.interceptor;

import com.fly.bos.entity.User;
import com.fly.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
//		ActionProxy proxy = invocation.getProxy();
//		String actionName = proxy.getActionName();
//		String namespace = proxy.getNamespace();
//		String url = namespace + actionName;
//		System.out.println(url);
		
		User user = BOSUtils.getLoginUser();
		if (user == null) {
			// 还未登陆，跳转到登陆页面
			return "login";
		}
		// 放行
		return invocation.invoke();
	}

}
