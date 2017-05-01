package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.Application;
import org.lzx.hros.domain.Attend;
import org.lzx.hros.domain.AttendType;
import org.lzx.hros.domain.Employee;

public interface AttendDao {
	/**
	 * 根据id返回Attend实例
	 * @param id
	 * @return
	 */
	Attend get(Integer id);
	
	/**
	 * 持久化指定Attend实例
	 * @param application
	 * @return
	 */
	Integer save(Attend attend);
	
	/**
	 * 修改Attend实例
	 * @param attend
	 */
	void update(Attend attend);
	
	/**
	 * 删除Attend实例
	 * @param attend
	 */
	void delete(Attend attend);
	
	/**
	 * 根据id号删除对应的Attend实例
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 查询全部Attend实例
	 * @return
	 */
	List<Attend> findAll();
	
	/**
	 * 根据员工查询该员工所有打卡记录
	 * @param employee
	 * @return
	 */
	List<Attend> findByEmp(Employee employee);


	/**
	 * 根据员工、日期查询该员工打卡记录集合
	 * @param employee
	 * @param dutyDay
	 * @return
	 */
	List<Attend> findByEmpAndDutyDay(Employee employee,String dutyDay);
	
	
	/**
	 * 根据员工、日期、是否上下班 查询该员工打卡记录集合
	 * @param employee
	 * @param dutyDay
	 * @param isCome
	 * @return 某天的上班或者下班的打卡记录
	 */
	Attend findByEmpAndDutyDayAndCome(Employee employee,
			String dutyDay,boolean isCome);
	
	/**
	 * 查看员工非正常打卡记录
	 * @param employee
	 * @param type
	 * @return
	 */
	List<Attend> findByEmpUnAttend(Employee employee,AttendType type);
}
