package com.cjlu.newspublish.interceptor;

import com.cjlu.newspublish.actions.BaseAction;
import com.cjlu.newspublish.actions.impl.AdminAction;
import com.cjlu.newspublish.actions.impl.LoginAction;
import com.cjlu.newspublish.actions.impl.RegAction;
import com.cjlu.newspublish.models.security.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		BaseAction baseAction = (BaseAction) invocation.getAction();
		// 如果是RegAction或LoginAction，则放行
		if (baseAction instanceof RegAction
				|| baseAction instanceof AdminAction
				|| baseAction instanceof LoginAction) {
			return invocation.invoke();
		} else {
			Admin admin = (Admin) invocation.getInvocationContext()
					.getSession().get("admin");
			if (admin == null) {
				// 如果admin为空，则跳转到登录页面
				return "login";
			} else {
				// if (baseAction instanceof UserAware) {
				// ((UserAware) baseAction).setUser(user);
				// }
				// admin不为空，放行
				return invocation.invoke();
			}
		}

	}

}
