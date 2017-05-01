package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.Employee;
import org.lzx.hros.domain.Manager;

public interface EmployeeDao {
	
	/**
	 * 根据标识属性来加载Employee实例
	 * @param id 需要加载的Employee实例的标识属性值
	 * @return 指定标识属性对应的Employee实例
	 */
	Employee get(Integer id);

	/**
	 * 持久化指定的Employee实例
	 * @param employee 需要被持久化的Employee实例
	 * @return Employee实例被持久化后的标识属性值
	 */
	Integer save(Employee employee);

	/**
	 * 修改指定的Employee实例
	 * @param employee 需要被修改的Employee实例
	 */
	void update(Employee employee);

	/**
	 * 删除指定的Employee实例
	 * @param employee 需要被删除的Employee实例
	 */
	void delete(Employee employee);

	/**
	 * 根据标识属性删除Employee实例
	 * @param id 需要被删除的Employee实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Employee实例
	 * @return 数据库中全部的Employee实例
	 */
	List<Employee> findAll();
	
	/**
	 * 根据用户名和密码查询员工
	 * @param emp
	 * @return
	 */
	List<Employee> findByNameAndPass(Employee emp);
	
	/**
	 * 根据员工姓名查询员工
	 * @param emp
	 * @return
	 */
	Employee findByName(String name);
	
	/**
	 * 根据登录名查找员工
	 * @param emp
	 * @return
	 */
	Employee findByUsername(Employee emp);
	
	/**
	 * 根据经理查询所辖员工
	 * @param mgr
	 * @return
	 */
	List<Employee> findByMgr(Manager mgr);
}
