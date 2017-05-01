package org.lzx.hros.action;

import java.util.List;

import org.lzx.hros.service.MgrManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewEmpAction extends ActionSupport {

	@Autowired
	private MgrManager mgr;

	// 封装当前经理所有员工的List
	private List emps;

	// emps属性的setter和getter方法
	public void setEmps(List emps) {
		this.emps = emps;
	}

	public List getEmps() {
		return this.emps;
	}

	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String mgrName = (String) ctx.getSession().get(WebConstant.USER);
		setEmps(mgr.getEmpsByMgr(mgrName));
		return SUCCESS;
	}
}
