package org.lzx.hros.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.druid.sql.visitor.functions.If;

public class Attend implements Serializable {

	private static final long serialVersionUID = 988874912491602137L;

	// 标识属性
	private Integer id;
	// 出勤日期
	private String dutyDay;
	// 打卡时间
	private Date punchTime;
	// 是否为上班打卡(boolean默认为false，即下班打卡)
	// 在Service层的autoPunch方法中会根据时间修改该属性
	private boolean isCome;
	// 出勤类型
	private AttendType type;
	// 出勤关联员工
	private Employee employee;

	public Attend() {

	}

	public Attend(Integer id, String dutyDay, Date punchTime, boolean isCome, AttendType type, Employee employee) {
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDutyDay() {
		return dutyDay;
	}

	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}

	public Date getPunchTime() {
		return punchTime;
	}

	public void setPunchTime(Date punchTime) {
		this.punchTime = punchTime;
	}

	//isCome属性的setter和getter方法（重要，必须要有get/set关键字，否则hibernate无法映射出对应实体）
	public void setIsCome(boolean isCome)
	{
		this.isCome = isCome;
	}
	public boolean getIsCome()
	{
		return this.isCome;
	}

	public AttendType getType() {
		return type;
	}

	public void setType(AttendType type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		if (getIsCome()) {
			return dutyDay.hashCode() + 29 * employee.hashCode() + 1;
		}
		return dutyDay.hashCode() + 29 * employee.hashCode();
	}

	/**
	 * 根据employee 、 iscome 、 dutyday来判定是否是相同的打卡记录
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj != null && obj.getClass() == Attend.class) {
			Attend attend = (Attend) obj;
			return getEmployee().equals(attend.getEmployee()) && getDutyDay().equals(attend.getDutyDay())
					&& getIsCome() == attend.getIsCome();
		}
		return false;

	}

}
