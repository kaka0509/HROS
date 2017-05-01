package org.lzx.hros.service;

import java.util.List;

import org.lzx.hros.domain.Employee;
import org.lzx.hros.utils.MyException;
import org.lzx.hros.vo.AppBean;
import org.lzx.hros.vo.EmpBean;
import org.lzx.hros.vo.SalaryBean;

public interface MgrManager {
	/**
	 * 新增员工
	 * 
	 * @param emp
	 *            新增的员工
	 * @param mgr
	 *            员工所属的经理
	 */
	void addEmp(Employee emp, String mgr) throws MyException;

	/**
	 * 根据经理返回所有的部门上个月工资
	 * 
	 * @param mgr经理名
	 *            
	 * @return 部门上个月工资
	 */
	List<SalaryBean> getSalaryByMgr(String mgr) throws MyException;

	/**
	 * 根据经理返回该部门的全部员工
	 * 
	 * @param mgr经理名
	 *            
	 * @return 经理的全部下属
	 */
	List<EmpBean> getEmpsByMgr(String mgr) throws MyException;

	/**
	 * 根据经理返回该部门的没有批复的申请
	 * 
	 * @param mgr
	 *            经理名
	 * @return 该部门的全部申请
	 */
	List<AppBean> getAppsByMgr(String mgr) throws MyException;

	/**
	 * 处理申请
	 * 
	 * @param appid
	 *            申请ID
	 * @param mgrName
	 *            经理名字
	 * @param result
	 *            是否通过
	 */
	void check(int appid, String mgrName, boolean result);
}
