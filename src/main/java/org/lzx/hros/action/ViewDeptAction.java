package org.lzx.hros.action;

import java.util.List;

import org.lzx.hros.service.MgrManager;
import org.lzx.hros.vo.SalaryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewDeptAction extends ActionSupport {

	@Autowired
	private MgrManager mgr;

	// 封装发薪列表的List属性
	private List sals;

	// sals属性的setter和getter方法
	public void setSals(List sals) {
		this.sals = sals;
	}

	public List getSals() {
		return this.sals;
	}

	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String mgrName = (String) ctx.getSession().get(WebConstant.USER);
		// 调用业务逻辑方法取得当前员工的全部发薪列表
		List<SalaryBean> result = mgr.getSalaryByMgr(mgrName);
		System.out.println("-----全部薪水列表-------" + result);
		setSals(result);
		return SUCCESS;
	}
}
