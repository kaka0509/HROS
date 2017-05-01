package org.lzx.hros.action;

import org.lzx.hros.service.EmpManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 实际处理打卡的Action
 * @author Skye
 *
 */

public class ProcessPunchAction extends ActionSupport {
	// 该Action所依赖的业务逻辑组件
	private EmpManager empMgr;
	// 封装处理结果的tip属性
	private String tip;

	// 依赖注入业务逻辑组件的setter方法
	public void setEmpManager(EmpManager empMgr) {
		this.empMgr = empMgr;
	}

	// tip属性的setter和getter方法
	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return this.tip;
	}

	// 处理上班打卡的方法
	public String come() throws Exception {
		return process(true);
	}

	// 处理下班打卡的方法
	public String leave() throws Exception {
		return process(false);
	}

	private String process(boolean isCome) throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user属性
		String user = (String) ctx.getSession().get(WebConstant.USER);
		System.out.println("-----打卡----   " + user);
		String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
		// 调用业务逻辑方法处理打卡请求
		int result = empMgr.punch(user, dutyDay, isCome);
		switch (result) {
		case EmpManager.PUNCH_FAIL:
			setTip("打卡失败");
			break;
		case EmpManager.PUNCHED:
			setTip("您已经打过卡了，不要重复打卡");
			break;
		case EmpManager.PUNCH_SUCC:
			setTip("打卡成功");
			break;
		}
		return SUCCESS;
	}
}
