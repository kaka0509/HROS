package org.lzx.hros.dao.impl;

import java.util.List;

import org.lzx.hros.dao.ApplicationDao;
import org.lzx.hros.domain.Application;
import org.lzx.hros.domain.Employee;
import org.lzx.hros.utils.MyHibernateDaoSupport;

public class ApplicationDaoImpl extends MyHibernateDaoSupport implements ApplicationDao {

	public Application get(Integer id) {
		return this.getHibernateTemplate().get(Application.class, id);
	}

	public Integer save(Application application) {
		return (Integer) this.getHibernateTemplate().save(application);
	}

	public void update(Application application) {
		this.getHibernateTemplate().update(application);
	}

	public void delete(Application application) {
		this.getHibernateTemplate().delete(application);
	}

	public void deleteById(Integer id) {
		// 先调用本类的 get（Integer id）方法取得对应的application对象
		this.getHibernateTemplate().delete(get(id));
	}

	public List<Application> findAll() {

		return (List<Application>) this.getHibernateTemplate().find("from Application");
	}

	public List<Application> findByEmp(Employee employee) {
		return (List<Application>) this.getHibernateTemplate()
				.find("from Application as a where " + "a.attend.employee=?", employee);
	}

}
