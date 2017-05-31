package org.lzx.hros.action.authority;

import org.lzx.hros.action.WebConstant;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 身份验证拦截器
 * 
 * @author Skye
 *
 */

public class EmpAuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取ActionContext对应的HttpSession中的level属性
		String level = (String) ctx.getSession().get(WebConstant.LEVEL);
		// 如果level不为null，且level为mgr
		// 如果level不为null，且level为emp或mgr
		if (level != null && (level.equals(WebConstant.EMP_LEVEL) || level.equals(WebConstant.MGR_LEVEL))) {
			return invocation.invoke();
		} else {
			//返回全局Result映射中的login视图
			return Action.LOGIN;
		}
	}

}
