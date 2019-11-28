package com.yonyou.controller;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.Unitl.GetIPAddr;
import com.yonyou.mapping.UserMapper;
import com.yonyou.pojo.User;
import com.yonyou.service.UserService;

@Controller
public class UserController {
	
   
	
	
	
	@Autowired
	HttpServletResponse response;

	@Autowired
	UserService userService;
	
	@GetMapping("/regs")
	public String res() {
		
		String info = "注册界面";
		//request.getSession().setAttribute("info", info);
		return "reg";
	}
	
	@GetMapping("/")
	public String index() {

		return "login";
		
	}	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@PostMapping("/userLogin")	
	public String loin(HttpServletRequest request,HttpServletResponse response,Model model) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new User();		
		user = userService.findUserByName(name,password);
		if(user == null) {
			try {
				//request.getSession().setAttribute("info", "登录失败");
				model.addAttribute("error", "用户名或密码错误");
				//request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);			    
			    return "login";
			} catch (Exception e) {
			}
		}
	
		request.getSession().setAttribute("user", user);	
		return "center";				
	}
	 public static String getIP() {
		 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		 String ip = GetIPAddr.getipAddr(request);
		 System.out.println(ip);
		 return ip;
		 
	 }
	
	@RequestMapping("/upload")
	public String upload() {
		
		return "upload";
	}
	@RequestMapping("/runCase")
	public String runCase() {
		
		return "selectFile";
	}
	
	
	

}
