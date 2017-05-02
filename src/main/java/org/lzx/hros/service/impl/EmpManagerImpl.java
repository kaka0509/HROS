package org.lzx.hros.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.lzx.hros.domain.AttendType;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.domain.Manager;
import org.lzx.hros.domain.Payment;
import org.lzx.hros.service.EmpManager;
import org.lzx.hros.vo.AttendBean;
import org.lzx.hros.vo.PaymentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EmpManagerImpl implements EmpManager {

	
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
	 * 验证是否以经理身份验证登录
	 * 
	 * @param 登录的经理身份信息
	 * @return 身份确认信息：0失败 ，1emp 2mgr
	 */
	public int validLogin(Manager mgr) {
		// 如果找到一个经理，以经理身份登录
		if (mgrDao.findByNameAndPass(mgr).size() >= 1) {
			return LOGIN_MGR;
		}
		// 如果找到员工，以普通员工身份登录（经理是员工子类，所以可以当父类参数传）
		else if (empDao.findByNameAndPass(mgr).size() >= 1) {
			return LOGIN_EMP;
		} else {
			return LOGIN_FAIL;
		}
	}

	/**
	 * 自动打卡方法，工作日早7点为员工插入旷工记录。员工自主打卡后进行修改。
	 */
	
	public void autoPunch() {
		System.out.println("自动插入旷工记录!");
		List<Employee> emps = empDao.findAll();
		// 获取当前时间		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 格式化当前时间
		String dutyDay = sdf.format(new Date());
		for (Employee e : emps) {
			// 获取旷工对应的出勤类型(数据库中对应id为6)
			AttendType atype = typeDao.get(6);			
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(atype);
			// 如果当前时间是早上 （将IsCome属性修改为上班打卡）
			if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT) {
				a.setIsCome(true);
			} else {
				// 下班打卡
				a.setIsCome(false);
			}
			a.setEmployee(e);
			attendDao.save(a);			
		}
		System.out.println("自动打卡完成");
	}

	/**
	 * 每月1号自动结算上月工资
	 */
	public void autoPay() {
		System.out.println("自动插入工资结算");
		List<Employee> emps = empDao.findAll();
		// 获取上个月时间
		Calendar c = Calendar.getInstance();
		// 当天时间往前倒退15天
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		// 为每个员工计算上个月工资
		for (Employee e : emps) {
			Payment pay = new Payment();
			// 获取该员工工资
			double amount = e.getSalary();
			// 获取出勤记录
			List<Attend> attends = attendDao.findByEmp(e);
			// 用工资累积其出勤记录工资
			for (Attend a : attends) {
				amount += a.getType().getAmerce();
			}
			// 添加工资结算
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}

	/**
	 * 验证某个员工是否可以打卡
	 * 
	 * @param user
	 *            员工名
	 * @param dutyDay
	 *            日期
	 * @return 可打卡类型
	 */
	public int validPunch(String user, String dutyDay) {
		Employee emp = empDao.findByName(user);
		// 找不到对应用户，返回无法打卡
		if (emp == null) {
			System.out.println("未找到对应用户");
			return NO_PUNCH;
		}
		// 找到员工当前的出勤记录
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
		// 系统没有为用户在当前dutyDay插入空打卡记录，无法打卡。
		if (attends == null || attends.size() <= 0) {
			System.out.println("系统未建立空打卡记录，打卡失败");
			return NO_PUNCH;
		}
		// 开始上班打卡(空打卡记录存在 ，且已准时到岗，且没有主动打卡导致打卡时间为空)
		else if (attends.size() == 1 && attends.get(0).getIsCome() && attends.get(0).getPunchTime() == null) {
			System.out.println("IsCome属性为真，表示该次打卡为上班打卡");
			return COME_PUNCH;
		} else if (attends.size() == 1 && attends.get(0).getPunchTime() == null) {
			System.out.println("该次打卡为下班打卡");
			return LEAVE_PUNCH;
		} else if (attends.size() == 2) {
			// 可以上班、下班打卡
			if (attends.get(0).getPunchTime() == null || attends.get(1).getPunchTime() == null) {
				return BOTH_PUNCH;
			}
			// 可以下班打卡
			if (attends.get(1).getPunchTime() == null) {
				return LEAVE_PUNCH;
			}

		} else {
			return NO_PUNCH;
		}

		return NO_PUNCH;
	}

	/**
	 * 员工打卡方法
	 */
	public int punch(String user, String dutyDay, boolean isCome) {
		Employee emp = empDao.findByName(user);
		if (emp == null) {
			System.out.println("当前员工不存在");
			return NO_PUNCH;
		}
		// 找到员工本次打卡对应的出勤记录
		Attend attend = attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
		if (attend == null) {
			System.out.println("考勤记录还没有自动生成，增加考勤记录失败！");
			return PUNCH_FAIL;
		}
		// 已经打卡
		if (attend.getPunchTime() != null) {
			System.out.println("已经打过卡了！");
			return PUNCHED;
		}
		System.out.println("==============打卡===============");
		// 获取打卡时间
		int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		// 上班打卡
		if (isCome) {
			// 9点前算正常
			if (punchHour < COME_LIMIT) {
				attend.setType(typeDao.get(1));
			}
			// 9点-11点前算迟到
			else if (punchHour < LATE_LIMIT) {
				attend.setType(typeDao.get(4));
			}
			// 11点后到记为旷工，不需要额外处理
		}
		// 下班打卡
		else {
			// 18点后正常下班
			if (punchHour >= LEAVE_LIMIT) {
				attend.setType(typeDao.get(1));
			}
			// 16-18点之间算早退
			else if (punchHour >= EARLY_LIMIT) {
				attend.setType(typeDao.get(5));
			}
		}
		// 手动打卡，修改自动打卡记录结束
		attendDao.update(attend);
		System.out.println("成功进行了打卡");
		return PUNCH_SUCC;
	}

	/**
	 * 根据员工姓名查看工资信息
	 */
	public List<PaymentBean> empSalary(String empName) {
		// 获取当前员工
		Employee emp = empDao.findByName(empName);
		// 获取当前员工全部工资列表
		List<Payment> pays = payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		// 封装VO集合，仅用于视图层展示
		for (Payment p : pays) {
			result.add(new PaymentBean(p.getPayMonth(), p.getAmount()));
		}
		return result;
	}

	/**
	 * 员工查看最近三天非正常打卡记录
	 */
	public List<AttendBean> unAttend(String empName) {
		// 找出正常上班
		AttendType type = typeDao.get(1);
		Employee emp = empDao.findByName(empName);
		// 找出非正常上班出勤记录
		List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		for (Attend att : attends) {
			result.add(new AttendBean(att.getId(), att.getDutyDay(), att.getType().getName(), att.getPunchTime()));
		}
		return result;

	}

	
	/**
	 * 返回全部的出勤类别
	 */
	public List<AttendType> getAllType() {
		
		return typeDao.findAll();
	}

	
	/**
	 * 添加申请
	 * @param attId 申请的出勤id
	 * @param typeId 申请的类型id
	 * @param reason 申请理由
	 * @return 添加申请的结果
	 */
	public boolean addApplication(int attId, int typeId, String reason) {
		//创建一个申请
		Application app = new Application();
		//获取申请需要改变的出勤记录
		Attend attend = attendDao.get(attId);
		AttendType type = typeDao.get(typeId);
		app.setAttend(attend);
		app.setType(type);
		if (reason!=null) {
			app.setReason(reason);
		}
		appDao.save(app);
		return true;
	}

}
