package com.fly.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.fly.bos.utils.PageBean;

/**
 * 持久层通用接口
 * @author 轻舞飞扬
 * @version
 * @date2017年8月19日
 * 
 */
public interface IBaseDao<T> {
	/**
	 * 保存方法
	 * @param t
	 */
	void save(T t);
	
	/**
	 * 删除方法
	 * @param t
	 */
	void delete(T t);
	
	/**
	 * 更新方法
	 * @param t
	 */
	void update(T t);
	
	
	/**
	 * 根据id来查询
	 * @param id
	 * @return
	 */
	T findById(Serializable id);
	
	
	/**
	 * 查询所有
	 * @param t
	 * @return
	 */
	List<T> findAll(T t);
	
	/**
	 * 通用修改方法
	 * @param queryName 对应hbm.xml配置的sql语句
	 * @param objects 可变参数
	 */
	public void executeUpdate(String queryName, Object...objects);
	
	/**
	 * 分页查询功能
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
	/**
	 * 保存或者更新功能，以防止主键冲突
	 * @param t
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 根据指定条件进行查询
	 * @param criteria
	 * @return List<T>
	 */
	public List<T> findByCriteria(DetachedCriteria criteria);
	
}
