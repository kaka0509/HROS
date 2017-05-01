package org.lzx.hros.domain;

import java.io.Serializable;

public class CheckBack implements Serializable {

	private static final long serialVersionUID = 5706665554766569008L;

	// 标识属性
	private Integer id;
	// 是否同意申请
	private boolean result;
	// 批复理由
	private String reason;
	// 该批复对应的申请
	private Application app;
	// 批复的经理
	private Manager manager;

	// 无参数的构造器
	public CheckBack() {
	}

	// 初始化全部属性的构造器
	public CheckBack(Integer id, boolean result, String reason, Application app, Manager manager) {
		this.id = id;
		this.result = result;
		this.reason = reason;
		this.app = app;
		this.manager = manager;
	}

	// id属性的setter和getter方法
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// result属性的setter和getter方法
	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return this.result;
	}

	// reason属性的setter和getter方法
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	// app属性的setter和getter方法
	public void setApp(Application app) {
		this.app = app;
	}

	public Application getApp() {
		return this.app;
	}

	// manager属性的setter和getter方法
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Manager getManager() {
		return this.manager;
	}
}
