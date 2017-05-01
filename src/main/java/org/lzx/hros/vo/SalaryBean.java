package org.lzx.hros.vo;

import java.io.Serializable;

public class SalaryBean implements Serializable {
	private static final long serialVersionUID = 48L;
	private String empName;
	private double amount;

	// 无参数的构造器
	public SalaryBean() {
	}

	// 初始化全部属性的构造器
	public SalaryBean(String empName, double amount) {
		this.empName = empName;
		this.amount = amount;
	}

	// empName属性的setter和getter方法
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpName() {
		return this.empName;
	}

	// amount属性的setter和getter方法
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}
}