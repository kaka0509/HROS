package org.lzx.hros.action;

import org.lzx.hros.domain.Employee;
import org.lzx.hros.service.MgrManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 经理人员新增新员工的Action
 * 
 * @author Skye
 *
 */
public class AddEmpAction extends ActionSupport {

	@Autowired
	protected MgrManager mgr;

	// 新增的员工的
	private Employee emp;
	// 封装提示信息的tip属性
	private String tip;

	// emp属性的setter和getter方法
	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Employee getEmp() {
		return this.emp;
	}

	// tip属性的setter和getter方法
	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return this.tip;
	}

	public String execute() throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String mgrName = (String) ctx.getSession().get(WebConstant.USER);
		// 添加新用户
		System.out.println("新增员工： "+ emp);
		mgr.addEmp(emp, mgrName);
		setTip("新增员工成功");
		return SUCCESS;
	}

}
