package com.fly.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.fly.bos.dao.base.IBaseDao;

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
		this.getHibernateTemplate().get(entityClass, id);
		return null;
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
			// 赋值
			query.setParameter(count++, object);
		}
		// 执行更新
		query.executeUpdate();
	}

}
