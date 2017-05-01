package org.lzx.hros.dao.impl;

import java.util.List;

import org.lzx.hros.dao.EmployeeDao;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.domain.Manager;
import org.lzx.hros.utils.MyHibernateDaoSupport;

public class EmployeeDaoImpl extends MyHibernateDaoSupport implements EmployeeDao {

	public Employee get(Integer id) {
		return this.getHibernateTemplate().get(Employee.class, id);
	}

	public Integer save(Employee employee) {
		return (Integer) this.getHibernateTemplate().save(employee);
	}

	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}

	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
	}

	public void delete(Integer id) {
		this.getHibernateTemplate().delete(get(id));
	}

	public List<Employee> findAll() {
		return (List<Employee>) this.getHibernateTemplate().find("from Employee");
	}

	public List<Employee> findByNameAndPass(Employee emp) {

		return (List<Employee>) this.getHibernateTemplate().find("from Employee p where p.name = ? and p.pass = ?",
				emp.getName(), emp.getPass());
	}

	/**
	 * 根据员工姓名查询员工
	 */
	public Employee findByName(String name)
	{
		List<Employee> emps = (List<Employee>)getHibernateTemplate()
			.find("from Employee where name = ? " , name);
		if (emps!= null && emps.size() >= 1)
		{
			return emps.get(0);
		}
		return null;
	}
	
	/**
	 * 根据登录名查找对应员工
	 * @param emp
	 * @return
	 */
	public Employee findByUsername(Employee emp) {
		List<Employee> emps = (List<Employee>) this.getHibernateTemplate().find("from Employee where username= ?",
				emp.getUsername());
		if (emps != null || emps.size() > 0) {
			return emps.get(0);
		}
		return null;
	}

	public List<Employee> findByMgr(Manager mgr) {
		return (List<Employee>)getHibernateTemplate()
				.find("from Employee as e where "
				+ "e.manager = ?" , mgr);
	}

}
