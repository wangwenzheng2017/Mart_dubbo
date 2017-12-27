package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.result.EasyUIResult;
import com.taotao.result.TaotaoResult;

public interface ItemService {

	public TbItem getItemById(long itemId);
	public EasyUIResult getItemList(int page,int rows);
	public TaotaoResult addItem(TbItem item,String desc);
	public TbItemDesc getItemDescById(long itemId);
	
}
