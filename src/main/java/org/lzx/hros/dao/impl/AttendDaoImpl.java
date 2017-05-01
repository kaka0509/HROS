package org.lzx.hros.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.lzx.hros.dao.AttendDao;
import org.lzx.hros.domain.Attend;
import org.lzx.hros.domain.AttendType;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.utils.MyHibernateDaoSupport;

public class AttendDaoImpl extends MyHibernateDaoSupport implements AttendDao {

	public Attend get(Integer id) {
		return this.getHibernateTemplate().get(Attend.class, id);
	}

	public Integer save(Attend attend) {

		return (Integer) this.getHibernateTemplate().save(attend);
	}

	public void update(Attend attend) {
		this.getHibernateTemplate().update(attend);
	}

	public void delete(Attend attend) {
		this.getHibernateTemplate().delete(attend);
	}

	public void deleteById(Integer id) {
		this.getHibernateTemplate().delete(get(id));
	}

	public List<Attend> findAll() {

		return (List<Attend>) this.getHibernateTemplate().find("from Attend");
	}

	public List<Attend> findByEmp(Employee employee) {

		return (List<Attend>) this.getHibernateTemplate().find("from Attend as a where a.employee=?", employee);

	}

	/**
	 * 多参数，以数组当参数的例子 查询该员工某天的打卡记录集合（包括上班、下班）
	 */
	public List<Attend> findByEmpAndDutyDay(Employee employee, String dutyDay) {
		return (List<Attend>) this.getHibernateTemplate()
				.find("from Attend as a where a.employee=? and " + "a.dutyDay=?", new Object[] { employee, dutyDay });
	}

	/**
	 * 该员工某天上班 or 下班的打卡记录
	 */
	public Attend findByEmpAndDutyDayAndCome(Employee employee, String dutyDay, boolean isCome) {

		List<Attend> al = findByEmpAndDutyDay(employee, dutyDay);
		if (al != null || al.size() > 0) {
			for (Attend attend : al) {
				if (attend.getIsCome() == isCome) {
					return attend;
				}
			}
		}
		return null;

	}

	/**
	 * 找出非正常上班的出勤记录
	 * @param employee 员工实体
	 * @param type 出勤类型（当前来说一般用 type_id:1那种）
	 */
	public List<Attend> findByEmpUnAttend(Employee employee, AttendType type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -3);
		String start = sdf.format(c.getTime());
		Object[] args = { employee, type, start, end };
		return (List<Attend>)this.getHibernateTemplate()
				.find("from Attend as a where a.employee=? and "+"a.type !=? and a.dutyDay between ? and ?", args);
	}

}
