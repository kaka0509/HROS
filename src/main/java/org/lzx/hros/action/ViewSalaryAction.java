package org.lzx.hros.action;

import java.util.List;

import org.lzx.hros.service.EmpManager;
import org.lzx.hros.vo.PaymentBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 查看工资的Action
 * @author Skye
 *
 */
public class ViewSalaryAction extends ActionSupport {

	@Autowired
	private EmpManager mgr;

	// 封装所有发薪信息的List
	private List salarys;

	// salarys属性的setter和getter方法
	public void setSalarys(List salarys) {
		this.salarys = salarys;
	}

	public List getSalarys() {
		return this.salarys;
	}

	// 处理用户请求的方法
	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String user = (String) ctx.getSession().get(WebConstant.USER);
		List<PaymentBean> salarys = mgr.empSalary(user);
		setSalarys(salarys);
		return SUCCESS;
	}

}
