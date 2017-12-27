package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;


@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
		//根据商品id查询商品信息
	    TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult saveItem(TbItem item,String desc) {
		//根据商品id查询商品信息
		TaotaoResult taotaoResult = itemService.addItem(item, desc);
		return taotaoResult;
	}
}
