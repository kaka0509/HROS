package org.lzx.hros.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.lzx.hros.dao.ApplicationDao;
import org.lzx.hros.dao.AttendDao;
import org.lzx.hros.dao.AttendTypeDao;
import org.lzx.hros.dao.CheckBackDao;
import org.lzx.hros.dao.EmployeeDao;
import org.lzx.hros.dao.ManagerDao;
import org.lzx.hros.dao.PaymentDao;
import org.lzx.hros.domain.Application;
import org.lzx.hros.domain.Attend;
import org.lzx.hros.domain.CheckBack;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.domain.Manager;
import org.lzx.hros.domain.Payment;
import org.lzx.hros.service.MgrManager;
import org.lzx.hros.utils.MyException;
import org.lzx.hros.vo.AppBean;
import org.lzx.hros.vo.EmpBean;
import org.lzx.hros.vo.SalaryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class MgrManagerImpl implements MgrManager {

	private ApplicationDao appDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private CheckBackDao checkDao;
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private PaymentDao payDao;
	
	

	public void setAppDao(ApplicationDao appDao) {
		this.appDao = appDao;
	}

	public void setAttendDao(AttendDao attendDao) {
		this.attendDao = attendDao;
	}

	public void setTypeDao(AttendTypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setCheckDao(CheckBackDao checkDao) {
		this.checkDao = checkDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
	}

	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}

	/**
	 * 经理人员新增员工
	 */
	public void addEmp(Employee emp, String mgr) throws MyException {

		Manager m = mgrDao.findByName(mgr);
		if (m == null) {
			throw new MyException("找不到对应经理，新增员工的业务异常");
		}
		emp.setManager(m);
		empDao.save(emp);
	}

	/**
	 * 根据经理查找所在的部门全部员工上个月工资
	 * 
	 * @param mgr 经理名字
	 *            
	 * @return 部门上个月工资
	 */
	public List<SalaryBean> getSalaryByMgr(String mgr) throws MyException {
		Manager m = mgrDao.findByName(mgr);
		if (m == null) {
			throw new MyException("您是经理吗？或你还未登录？");
		}
		// 查询该经理对应所有员工
		List<Employee> emps = empDao.findByMgr(m);
		// 部门依然没有员工
		if (emps == null || emps.size() < 1) {
			throw new MyException("您的部门没有员工");
		}
		// 取得上个月的日期
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		// 遍历本部门每个员工
		for (Employee e : emps) {
			Payment p = payDao.findByMonthAndEmp(payMonth, e);
			if (p != null) {
				result.add(new SalaryBean(e.getName(), p.getAmount()));
			}
		}
		return result;
	}

	/**
	 * 根据经理返回该部门的全部员工
	 * 
	 * @param mgr 经理名字
	 *            
	 * @return 经理的全部下属
	 */
	public List<EmpBean> getEmpsByMgr(String mgr) throws MyException {
		Manager m = mgrDao.findByName(mgr);
		if (m == null) {
			throw new MyException("您是经理吗？或你还未登录？");
		}
		// 查询该经理对应的全部员工
		List<Employee> emps = empDao.findByMgr(m);
		// 部门没有员工
		if (emps == null || emps.size() < 1) {
			throw new MyException("您的部门没有员工");
		}
		// 封装VO
		List<EmpBean> result = new ArrayList<EmpBean>();
		for (Employee emp : emps) {
			result.add(new EmpBean(emp.getName(), emp.getPass(), emp.getSalary()));
		}
		return result;
	}

	/**
	 * 根据经理返回该部门的没有批复的申请
	 * 
	 * @param mgr
	 *            经理名
	 * @return 该部门的全部申请
	 */
	public List<AppBean> getAppsByMgr(String mgr) throws MyException {
		Manager m = mgrDao.findByName(mgr);
		if (m == null) {
			throw new MyException("您是经理吗？或你还未登录？");
		}
		List<Employee> emps = empDao.findByMgr(m);
		// 部门依然没有员工
		if (emps == null || emps.size() < 1) {
			throw new MyException("您的部门没有员工");
		}
		// 封装VO
		List<AppBean> result = new ArrayList<AppBean>();
		for (Employee e : emps) {
			// 查看该员工的全部申请
			List<Application> apps = appDao.findByEmp(e);
			if (apps != null && apps.size() > 0) {
				for (Application app : apps) {
					// 只选择未处理的申请
					if (app.getResult() == false) {
						Attend attend = app.getAttend();
						result.add(new AppBean(app.getId(), e.getName(), attend.getType().getName(),
								app.getType().getName(), app.getReason()));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 处理申请
	 * @param appid 申请ID
	 * @param mgrName 经理名字
	 * @param result 是否通过
	 */
	public void check(int appid, String mgrName, boolean result) {
		Application app = appDao.get(appid);
		CheckBack check = new CheckBack();
		Manager m = mgrDao.findByName(mgrName);
		check.setManager(m);
		check.setApp(app);
		//同意申请
		if (result) {
			//设置通过申请
			check.setResult(result);
			//修改申请为已经批复
			app.setResult(true);
			appDao.save(app);
			//申请通过时，对考勤记录类型进行修改。考勤记录是异常考勤申请的一个成员变量，可以从申请中获取
			Attend attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}
		else {
			//没有通过申请
			check.setResult(false);
			//修改申请为已经批复
			app.setResult(true);
			appDao.save(app);
		}
		//保存批复
		checkDao.save(check);
	}

}
