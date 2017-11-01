package com.wxc.quick.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wxc.quick.model.User;
import com.wxc.quick.service.AccountService;

public class MyShiroRealm extends AuthorizingRealm {
	private AccountService accountService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		// 根据自己系统规则的需要编写获取授权信息，这里为了快速只获取用户对应角色的资源url信息
		String username = (String) pc.fromRealm(getName()).iterator().next();
		if (username != null) {
			List<String> pers = accountService.getPermissionsByUserName(username);
			if (pers != null && !pers.isEmpty()) {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				for (String each : pers) {
					info.addStringPermission(each);
				}
				return info;
			}
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
		UsernamePasswordToken token=(UsernamePasswordToken) at;
		String username=token.getUsername();
		if(username!=null&&!"".equals(username)) {
			User user=accountService.getUserByUserName(username);
			if(user!=null)
				return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
		}
		return null;
	}

}
