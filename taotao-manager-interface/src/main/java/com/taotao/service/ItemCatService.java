package com.taotao.service;

import java.util.List;

import com.taotao.result.EasyUITreeNode;

public interface ItemCatService {

	public List<EasyUITreeNode> getItemCatList(long parentId);
	
}
