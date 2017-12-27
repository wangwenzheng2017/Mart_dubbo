package com.taotao.content.service;

import java.util.List;

import com.taotao.pojo.TbContent;
import com.taotao.result.TaotaoResult;

public interface ContentService {
	public TaotaoResult addContent(TbContent content);
	public List<TbContent> getContentList(long cid);
}
