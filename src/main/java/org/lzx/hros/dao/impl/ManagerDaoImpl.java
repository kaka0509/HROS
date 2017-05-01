package org.lzx.hros.dao.impl;

import java.util.List;

import org.lzx.hros.dao.ManagerDao;
import org.lzx.hros.domain.Manager;
import org.lzx.hros.utils.MyHibernateDaoSupport;

public class ManagerDaoImpl extends MyHibernateDaoSupport implements ManagerDao {

	/**
	 * 根据标识属性来加载Manager实例
	 * 
	 * @param id
	 *            需要加载的Manager实例的标识属性值
	 * @return 指定标识属性对应的Manager实例
	 */
	public Manager get(Integer id) {
		return getHibernateTemplate().get(Manager.class, id);
	}

	/**
	 * 持久化指定的Manager实例
	 * 
	 * @param manager
	 *            需要被持久化的Manager实例
	 * @return Manager实例被持久化后的标识属性值
	 */
	public String save(Manager manager) {
		return (String) getHibernateTemplate().save(manager);
	}

	/**
	 * 修改指定的Manager实例
	 * 
	 * @param manager
	 *            需要被修改的Manager实例
	 */
	public void update(Manager manager) {
		getHibernateTemplate().update(manager);
	}

	/**
	 * 删除指定的Manager实例
	 * 
	 * @param manager
	 *            需要被删除的Manager实例
	 */
	public void delete(Manager manager) {
		getHibernateTemplate().delete(manager);
	}

	/**
	 * 根据标识属性删除Manager实例
	 * 
	 * @param id
	 *            需要被删除的Manager实例的标识属性值
	 */
	public void delete(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	/**
	 * 查询全部的Manager实例
	 * 
	 * @return 数据库中全部的Manager实例
	 */
	public List<Manager> findAll() {
		return (List<Manager>) getHibernateTemplate().find("from Manager");
	}

	/**
	 * 根据用户名和密码查询经理
	 * 
	 * @param emp
	 *            包含指定用户名、密码的经理
	 * @return 符合指定用户名和密码的经理
	 */
	public List<Manager> findByNameAndPass(Manager mgr) {
		return (List<Manager>) getHibernateTemplate().find("from Manager m where m.name = ? and m.pass=?",
				mgr.getName(), mgr.getPass());
	}

	/**
	 * 根据Username查找经理
	 */
	public Manager findByUsername(Manager mgr) {
		return (Manager) this.getHibernateTemplate().find("from Manager where username=?", mgr.getUsername());
	}

	/**
	 * 根据名字查找经理人员（同名的给个列表）
	 */
	public Manager findByName(String name) {

		List<Manager> ml = (List<Manager>) getHibernateTemplate().find("from Manager m where m.name=?", name);
		if (ml != null && ml.size() > 0) {
			return ml.get(0);
		}
		return null;
	}
}
