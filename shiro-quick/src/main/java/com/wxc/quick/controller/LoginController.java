package com.wxc.quick.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxc.quick.model.User;
import com.wxc.quick.service.AccountService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "login")
	public ModelAndView Login(String username, String password) {
		ModelAndView mav = new ModelAndView();
		User user = accountService.getUserByUserName(username);
		if (user == null) {
			mav.setViewName("toLogin");
			mav.addObject("msg", "用户不存在");
			return mav;

		}

		if (!user.getPassword().equals(password)) {
			mav.setViewName("toLogin");
			mav.addObject("msg", "帐号密码错误");
			return mav;
		}
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		Subject subject=SecurityUtils.getSubject();
		subject.login(token);
		mav.setViewName("redirect:/home");
		return mav;
	}

}
