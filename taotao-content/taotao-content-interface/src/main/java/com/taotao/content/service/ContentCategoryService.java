package com.taotao.content.service;

import java.util.List;

import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;

public interface ContentCategoryService {

	public List<EasyUITreeNode> getContentCategoryList(long parentId);
	public TaotaoResult addContentCategory(long parentId,String name);
	
}
