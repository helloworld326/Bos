package com.fly.bos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.fly.bos.dao.base.IBaseDao;
import com.fly.bos.utils.PageBean;

/**
 * 持久层通用实现
 * @author 轻舞飞扬
 * @version
 * @date2017年8月19日
 * @param <T>
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	// 代表某个运行期的Class对象
	private Class<T> entityClass;
	
	// 根据类型注入spring配置中sessionFactory工厂对象
	@Resource
	public void setMySessionFactory(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	// 在父类构造中动态获取entityClass;
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll(T t) {
		String hql = "from " + entityClass.getName();
		List<T> list = (List<T>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int count = 0;
		for (Object object : objects) {
			query.setParameter(count++, object);
		}
		query.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getCriteria();
		// 查询total总量
		criteria = criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			pageBean.setTotal(list.get(0).intValue());
		}
		// 查询rows -- 数据的集合
		criteria.setProjection(null);
		// 指定hibenate封装对象的方式
		criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		// criteria为空表示查询所有
		List rows = this.getHibernateTemplate().findByCriteria(criteria, (currentPage - 1) * pageSize, pageSize);
		pageBean.setRows(rows);
		
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	

}
