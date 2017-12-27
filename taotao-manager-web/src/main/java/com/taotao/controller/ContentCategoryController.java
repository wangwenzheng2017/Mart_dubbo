package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.content.service.ContentCategoryService;
import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired 
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId) {
		List<EasyUITreeNode> contentCategoryList = contentCategoryService.getContentCategoryList(parentId);
		return contentCategoryList;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createCatogary(Long parentId,String name) {
		TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
}
