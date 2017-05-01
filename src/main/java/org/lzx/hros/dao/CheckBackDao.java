package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.CheckBack;

public interface CheckBackDao
{
	/**
	 * 根据标识属性来加载CheckBack实例
	 * @param id 需要加载的CheckBack实例的标识属性值
	 * @return 指定标识属性对应的CheckBack实例
	 */
	CheckBack get(Integer id);

	/**
	 * 持久化指定的CheckBack实例
	 * @param checkBack 需要被持久化的CheckBack实例
	 * @return CheckBack实例被持久化后的标识属性值
	 */
	Integer save(CheckBack checkBack);

	/**
	 * 修改指定的CheckBack实例
	 * @param checkBack 需要被修改的CheckBack实例
	 */
	void update(CheckBack checkBack);

	/**
	 * 删除指定的CheckBack实例
	 * @param checkBack 需要被删除的CheckBack实例
	 */
	void delete(CheckBack checkBack);

	/**
	 * 根据标识属性删除CheckBack实例
	 * @param id 需要被删除的CheckBack实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的CheckBack实例
	 * @return 数据库中全部的CheckBack实例
	 */
	List<CheckBack> findAll();
}
