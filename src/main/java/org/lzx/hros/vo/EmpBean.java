package org.lzx.hros.vo;

import java.io.Serializable;

public class EmpBean implements Serializable
{
	private static final long serialVersionUID = 48L;
	private String empName;
	private String empPass;
	private double amount;

 	//无参数的构造器
	public EmpBean()
	{
	}
	//初始化全部属性的构造器
	public EmpBean(String empName , String empPass
		, double amount)
	{
		this.empName = empName;
		this.empPass = empPass;
		this.amount = amount;
	}

	//empName属性的setter和getter方法
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getEmpName()
	{
		return this.empName;
	}

	//empPass属性的setter和getter方法
	public void setEmpPass(String empPass)
	{
		this.empPass = empPass;
	}
	public String getEmpPass()
	{
		return this.empPass;
	}

	//amount属性的setter和getter方法
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public double getAmount()
	{
		return this.amount;
	}
}