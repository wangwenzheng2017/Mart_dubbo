package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.taotao.pojo.TbUser;
import com.taotao.result.TaotaoResult;
import com.taotao.sso.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Value("${COOKIE_TOKEN_KEY}")
	private String COOKIE_TOKEN_KEY;
	
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public TaotaoResult checkData(@PathVariable String param,@PathVariable Integer type){
		TaotaoResult result = userService.checkData(param, type);
		return result;
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user){
		TaotaoResult result = userService.createUser(user);
		return result;
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		TaotaoResult result = userService.login(username,password);
		System.out.println("result.getData():"+result.getData());
		Integer status  = result.getStatus();
		//排除错误情况
		if (status == 400) {
			return result;
		}
		//正常
		String token = result.getData().toString();
		CookieUtils.setCookie(request, response, COOKIE_TOKEN_KEY, token);
		return result;
	}
	
	@RequestMapping(value="/user/token/{token}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback){
		
		TaotaoResult result = userService.getUserByToken(token);
		//是否是jsonp请求
		if (StringUtils.isNotEmpty(callback)) {
			String strResult = callback + "(" + JsonUtils.objectToJson(result) + ");";
			return strResult;
		}
		return JsonUtils.objectToJson(result);
	}
	
		
}
