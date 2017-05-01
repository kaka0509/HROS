package org.lzx.hros.action;

import java.util.List;

import org.lzx.hros.service.EmpManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 发起考勤修改申请时，查找所有考勤类别交前端
 * @author Skye
 *
 */
public class AppChangeAction extends ActionSupport {

	@Autowired
	private EmpManager mgr;

	// 封装所有异动的列表
	private List types;

	// types属性的setter和getter方法
	public void setTypes(List types) {
		this.types = types;
	}

	public List getTypes() {
		return this.types;
	}

	// 处理用户请求
	public String execute() throws Exception {
		setTypes(mgr.getAllType());
		return SUCCESS;
	}
}
