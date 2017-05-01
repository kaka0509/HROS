package org.lzx.hros.dao.impl;

import java.util.List;

import org.lzx.hros.dao.AttendTypeDao;
import org.lzx.hros.domain.AttendType;
import org.lzx.hros.utils.MyHibernateDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AttendTypeDaoImpl extends MyHibernateDaoSupport implements AttendTypeDao{

	/**
	 * 根据标识属性来加载AttendType实例
	 * @param id 需要加载的AttendType实例的标识属性值
	 * @return 指定标识属性对应的AttendType实例
	 */
	public AttendType get(Integer id)
	{
		return getHibernateTemplate()
			.get(AttendType.class , id);
	}

	/**
	 * 持久化指定的AttendType实例
	 * @param attendType 需要被持久化的AttendType实例
	 * @return AttendType实例被持久化后的标识属性值
	 */
	public Integer save(AttendType attendType)
	{
		return (Integer)getHibernateTemplate()
			.save(attendType);
	}

	/**
	 * 修改指定的AttendType实例
	 * @param attendType 需要被修改的AttendType实例
	 */
	public void update(AttendType attendType)
	{
		getHibernateTemplate()
			.update(attendType);
	}

	/**
	 * 删除指定的AttendType实例
	 * @param attendType 需要被删除的AttendType实例
	 */
	public void delete(AttendType attendType)
	{
		getHibernateTemplate()
			.delete(attendType);
	}

	/**
	 * 根据标识属性删除AttendType实例
	 * @param id 需要被删除的AttendType实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的AttendType实例
	 * @return 数据库中全部的AttendType实例
	 */
	public List<AttendType> findAll()
	{
		return (List<AttendType>)getHibernateTemplate()
			.find("from AttendType");
	}

	public List<AttendType> findByLike() {
		String key = "假";
		String hql = "from AttendType where name like ?";
		return (List<AttendType>) this.getHibernateTemplate().find(hql, "%"+key+"%");
	}
	
		
}
