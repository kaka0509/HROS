package org.lzx.hros.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Manager extends Employee implements Serializable {

	private static final long serialVersionUID = 8832769333875852656L;

	// 经理管理部门
	private String dept;
	// 经理的员工
	private Set<Employee> employees = new HashSet<Employee>();
	// 经理签署批复
	private Set<CheckBack> checks = new HashSet<CheckBack>();

	public Manager() {
	}

	public Manager(String dept, Set<Employee> employees, Set<CheckBack> checks) {
		this.dept = dept;
		this.employees = employees;
		this.checks = checks;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<CheckBack> getChecks() {
		return checks;
	}

	public void setChecks(Set<CheckBack> checks) {
		this.checks = checks;
	}

	@Override
	public String toString() {
		return "Manager [dept=" + dept + ", employees=" + employees + ", checks=" + checks + "]";
	}
	
	

}
