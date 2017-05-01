package org.lzx.hros.dao.impl;

import java.util.List;

import org.lzx.hros.dao.PaymentDao;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.domain.Payment;
import org.lzx.hros.utils.MyHibernateDaoSupport;

public class PaymentDaoImpl extends MyHibernateDaoSupport implements PaymentDao {
	/**
	 * 根据标识属性来加载Payment实例
	 * @param id 需要加载的Payment实例的标识属性值
	 * @return 指定标识属性对应的Payment实例
	 */
	public Payment get(Integer id)
	{
		return getHibernateTemplate()
			.get(Payment.class , id);
	}

	/**
	 * 持久化指定的Payment实例
	 * @param payment 需要被持久化的Payment实例
	 * @return Payment实例被持久化后的标识属性值
	 */
	public Integer save(Payment payment)
	{
		return (Integer)getHibernateTemplate()
			.save(payment);
	}

	/**
	 * 修改指定的Payment实例
	 * @param payment 需要被修改的Payment实例
	 */
	public void update(Payment payment)
	{
		getHibernateTemplate()
			.update(payment);
	}

	/**
	 * 删除指定的Payment实例
	 * @param payment 需要被删除的Payment实例
	 */
	public void delete(Payment payment)
	{
		getHibernateTemplate()
			.delete(payment);
	}

	/**
	 * 根据标识属性删除Payment实例
	 * @param id 需要被删除的Payment实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的Payment实例
	 * @return 数据库中全部的Payment实例
	 */
	public List<Payment> findAll()
	{
		return (List<Payment>)getHibernateTemplate()
			.find("from Payment");
	}

	/**
	 * 根据员工查询月结薪水
	 * @return 该员工对应的月结薪水集合
	 */ 
	public List<Payment> findByEmp(Employee emp)
	{
		return (List<Payment>)getHibernateTemplate()
			.find("from Payment as p where p.employee=?" , emp);
	}


	/**
	 * 根据员工和发薪月份来查询月结薪水
	 * @param payMonth 发薪月份
	 * @param emp 领薪的员工
	 * @return 指定员工、指定月份的月结薪水
	 */ 
	public Payment findByMonthAndEmp(String payMonth 
		 , Employee emp)
	{
		List<Payment> pays = (List<Payment>)getHibernateTemplate()
			.find("from Payment as p where p.employee=? and p.payMonth=?"
			, new Object[]{emp , payMonth});
		if (pays != null && pays.size() > 0)
		{
			return pays.get(0);
		}
		return null;
	}
}
