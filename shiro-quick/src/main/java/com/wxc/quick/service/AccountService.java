package com.wxc.quick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxc.quick.dao.BaseDao;
import com.wxc.quick.model.Permission;
import com.wxc.quick.model.Role;
import com.wxc.quick.model.User;
import com.wxc.quick.model.UserRole;

@Service
public class AccountService {
	@Autowired
	private BaseDao dao;

	public User getUserByUserName(String username) {
		User user = (User) dao.findObjectByHQL("from User where name=?", new Object[] { username });
		return user;
	}

	public List<String> getPermissionsByUserName(String username) {
		User user = getUserByUserName(username);
		if (user == null)
			return null;
		List<String> list = new ArrayList<String>();
		for (UserRole userRole : user.getUserRoles()) {
			Role role = userRole.getRole();
			List<Permission> permissions = dao.findAllByHQL("from Permission where roleId=?",
					new Object[] { role.getId() });
			for (Permission p : permissions) {
				list.add(p.getUrl());
			}
		}
		return list;
	}
}
