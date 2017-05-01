package org.lzx.hros.dao;

import java.util.List;

import org.lzx.hros.domain.Manager;

public interface ManagerDao {
	/**
	 * 根据标识属性来加载Manager实例
	 * @param id 需要加载的Manager实例的标识属性值
	 * @return 指定标识属性对应的Manager实例
	 */
	Manager get(Integer id);

	/**
	 * 持久化指定的Manager实例
	 * @param manager 需要被持久化的Manager实例
	 * @return Manager实例被持久化后的标识属性值
	 */
	String save(Manager manager);

	/**
	 * 修改指定的Manager实例
	 * @param manager 需要被修改的Manager实例
	 */
	void update(Manager manager);

	/**
	 * 删除指定的Manager实例
	 * @param manager 需要被删除的Manager实例
	 */
	void delete(Manager manager);

	/**
	 * 根据标识属性删除Manager实例
	 * @param id 需要被删除的Manager实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Manager实例
	 * @return 数据库中全部的Manager实例
	 */
	List<Manager> findAll();
	
	/**
	 * 根据用户名和密码查询经理
	 * @return
	 */
	List<Manager> findByNameAndPass(Manager mgr);
	
	/**
	 * 根据登录用户名查找经理 
	 * @param mgr
	 * @return
	 */
	Manager findByUsername(Manager mgr);
	
	/**
	 * 根据姓名查找经理
	 * @param mgr
	 * @return
	 */
	Manager findByName(String mgr);
	
	
}
