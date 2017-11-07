package com.wxc.quick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("home")
	public String index() {
		System.out.println("登陆成功");
		return "home";
	}
}
