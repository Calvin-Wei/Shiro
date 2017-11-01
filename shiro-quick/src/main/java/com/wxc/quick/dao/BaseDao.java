package com.wxc.quick.dao;

import java.util.List;

public interface BaseDao {

	public void addObject(Object object);

	public List findAllByHQL(String hql);

	public List findAllByHQL(String hql, Object[] args);

	public Object findObjectByHQL(String hql);

	public Object findObjectByHQL(String hql, Object[] args);

	public Object findObjectBySQL(String sql);

	public List findPage(String hql, int page, int size);

	public List findPage(String hql, int page, int size, Object[] args);

	public void delObject(Object object);

	public void updateObject(Object object);

	public void updateObjectByHQL(String hql);

	public void updateObjectByHQL(String hql, Object[] params);

	public List findAllBySQL(String sql);
}
