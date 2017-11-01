package com.wxc.quick.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

@Transactional
public class BaseDaoImpl implements BaseDao {

	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addObject(Object object) {
		sessionFactory.getCurrentSession().save(object);

	}

	@Override
	public List findAllByHQL(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List findAllByHQL(String hql, Object[] args) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query.list();
	}

	@Override
	public Object findObjectByHQL(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Object findObjectByHQL(String hql, Object[] args) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		List list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Object findObjectBySQL(String sql) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List list = query.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List findPage(String hql, int page, int size) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(size);
		query.setFirstResult(page);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public List findPage(String hql, int page, int size, Object[] args) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.setMaxResults(size);
		query.setFirstResult(page);
		List<Object> list = query.list();
		return list;
	}

	@Override
	public void delObject(Object object) {
		sessionFactory.getCurrentSession().delete(object);

	}

	@Override
	public void updateObject(Object object) {
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public void updateObjectByHQL(String hql) {
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void updateObjectByHQL(String hql, Object[] params) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.executeUpdate();

	}

	@Override
	public List findAllBySQL(String sql) {
		// TODO 自动生成的方法存根
		return null;
	}

}
