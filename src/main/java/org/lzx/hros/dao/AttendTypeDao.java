package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.AttendType;

public interface AttendTypeDao {
	/**
	 * 根据标识属性来加载AttendType实例
	 * 
	 * @param id
	 *            需要加载的AttendType实例的标识属性值
	 * @return 指定标识属性对应的AttendType实例
	 */
	AttendType get(Integer id);

	/**
	 * 持久化指定的AttendType实例
	 * 
	 * @param attendType
	 *            需要被持久化的AttendType实例
	 * @return AttendType实例被持久化后的标识属性值
	 */
	Integer save(AttendType attendType);

	/**
	 * 修改指定的AttendType实例
	 * 
	 * @param attendType
	 *            需要被修改的AttendType实例
	 */
	void update(AttendType attendType);

	/**
	 * 删除指定的AttendType实例
	 * 
	 * @param attendType
	 *            需要被删除的AttendType实例
	 */
	void delete(AttendType attendType);

	/**
	 * 根据标识属性删除AttendType实例
	 * 
	 * @param id
	 *            需要被删除的AttendType实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的AttendType实例
	 * 
	 * @return 数据库中全部的AttendType实例
	 */
	List<AttendType> findAll();
	
	List<AttendType> findByLike();
}