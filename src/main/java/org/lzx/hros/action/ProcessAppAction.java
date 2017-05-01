package org.lzx.hros.action;

import org.lzx.hros.service.EmpManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 提出异常申请，更改考勤类型的Action
 * @author Skye
 *
 */
public class ProcessAppAction extends ActionSupport {

	// 注入依赖的业务组件
	@Autowired
	private EmpManager mgr;

	// 申请异动的出勤ID
	private int attId;
	// 希望改变到出勤类型
	private int typeId;
	// 申请理由
	private String reason;
	// 处理结果
	private String tip;

	// attId属性的setter和getter方法
	public void setAttId(int attId) {
		this.attId = attId;
	}

	public int getAttId() {
		return this.attId;
	}

	// typeId属性的setter和getter方法
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeId() {
		return this.typeId;
	}

	// reason属性的setter和getter方法
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	// tip属性的setter和getter方法
	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return this.tip;
	}

	// 处理用户请求
	public String execute() throws Exception {
		// 处理异动申请
		boolean result = mgr.addApplication(attId, typeId, reason);
		// 如果申请成功
		if (result) {
			setTip("您已经申请成功，等待经理审阅");
		} else {
			setTip("申请失败，请注意不要重复申请");
		}
		return SUCCESS;
	}
}
