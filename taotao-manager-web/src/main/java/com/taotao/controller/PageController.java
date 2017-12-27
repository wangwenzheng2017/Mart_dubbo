package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EasyUIResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showIndex(@PathVariable String page){
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIResult getItemList(Integer page, Integer rows) {
		EasyUIResult result = itemService.getItemList(page, rows);
		return result;
	}

}
