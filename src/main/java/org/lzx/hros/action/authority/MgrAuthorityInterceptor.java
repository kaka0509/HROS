package org.lzx.hros.action.authority;

import org.lzx.hros.action.WebConstant;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 经理人员的身份验证拦截器
 * @author Skye
 *
 */

public class MgrAuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取ActionContext对应的HttpSession中的level属性
		String level = (String) ctx.getSession().get(WebConstant.LEVEL);
		//如果level不为null，且level为mgr
		if (null!=level && level.equals(WebConstant.MGR_LEVEL)) {
			return invocation.invoke();
		}
		else{
			return Action.LOGIN;
		}
	}

}
