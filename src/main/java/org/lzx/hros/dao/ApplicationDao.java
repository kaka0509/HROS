package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.Application;
import org.lzx.hros.domain.Employee;

public interface ApplicationDao {
	
	/**
	 * 根据id返回application实例
	 * @param id
	 * @return
	 */
	Application get(Integer id);
	
	/**
	 * 持久化指定application实例
	 * @param application
	 * @return
	 */
	Integer save(Application application);
	
	/**
	 * 修改application实例
	 * @param application
	 */
	void update(Application application);
	
	/**
	 * 删除application实例
	 * @param application
	 */
	void delete(Application application);
	
	/**
	 * 根据id号删除对应的Application实例
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 查找全部的application实例
	 * @return
	 */
	List<Application> findAll();
	
	/**
	 * 根据员工实例查询未处理的异常打卡记录申请
	 * @param employee
	 * @return
	 */
	List<Application> findByEmp(Employee employee);
}
