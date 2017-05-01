package org.lzx.hros.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	// 标识属性
	private Integer id;
	// 员工姓名
	private String name;
	// 员工登录名
	private String username;
	// 员工密码
	private String pass;
	// 员工工资
	private double salary;
	// 员工对应经理
	private Manager manager;
	// 员工出勤记录
	private Set<Attend> attends = new HashSet<Attend>();
	// 员工工资记录
	private Set<Payment> payments = new HashSet<Payment>();

	public Employee() {
	}

	public Employee(Integer id, String name, String username, String pass, double salary, Manager manager,
			Set<Attend> attends, Set<Payment> payments) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Attend> getAttends() {
		return attends;
	}

	public void setAttends(Set<Attend> attends) {
		this.attends = attends;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	/**
	 * 当username和pass相同时就认为是相同的
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj.getClass() == Employee.class) {
			Employee employee = (Employee) obj;
			return this.getUsername().equals(employee.getUsername()) && this.getPass().equals(employee.getPass());
		}

		return true;
	}

	@Override
	public int hashCode() {

		return name.hashCode() + pass.hashCode() * 17;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", pass=" + pass + ", salary="
				+ salary + ", manager=" + manager + ", attends=" + attends + ", payments=" + payments + "]";
	}

}
