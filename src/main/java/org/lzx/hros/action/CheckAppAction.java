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
public class CheckAppAction extends ActionSupport {

	@Autowired
	protected MgrManager mgr;

	// 需要被处理的申请ID
	private int appid;
	// 封装处理结果
	private String result;
	// 封装处理提示
	private String tip;

	// appid属性的setter和getter方法
	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getAppid() {
		return this.appid;
	}

	// result属性的setter和getter方法
	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return this.result;
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
		// 通过申请
		if (result.equals("pass")) {
			mgr.check(appid, mgrName, true);
		}
		// 拒绝申请
		else if (result.equals("deny")) {
			mgr.check(appid, mgrName, false);
		} else {
			throw new Exception("参数丢失");
		}
		setTip("处理员工申请成功");
		return SUCCESS;
	}
}
