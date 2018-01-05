package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.result.TaotaoResult;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements  SearchItemService{

	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importAllItems() throws Exception{
		// 1、查询所有商品数据。
		List<SearchItem> itemList = searchItemMapper.getItemList();
		// 2、创建一个SolrServer对象。
		// 3、为每个商品创建一个SolrInputDocument对象。
		for (SearchItem searchItem : itemList) {
			SolrInputDocument document = new SolrInputDocument();
			// 4、为文档添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			// 5、向索引库中添加文档。
			solrServer.add(document);
		}
		//提交修改
		solrServer.commit();
		// 6、返回TaotaoResult。
		return TaotaoResult.ok();
	}

	public TaotaoResult addDocument(long itemId) throws Exception {
		// 1、根据商品id查询商品信息。
		SearchItem searchItem = searchItemMapper.getItemById(itemId);
		// 2、创建一SolrInputDocument对象。
		SolrInputDocument document = new SolrInputDocument();
		// 3、使用SolrServer对象写入索引库。
		document.addField("id", searchItem.getId());
		document.addField("item_title", searchItem.getTitle());
		document.addField("item_sell_point", searchItem.getSell_point());
		document.addField("item_price", searchItem.getPrice());
		document.addField("item_image", searchItem.getImage());
		document.addField("item_category_name", searchItem.getCategory_name());
		document.addField("item_desc", searchItem.getItem_desc());
		// 5、向索引库中添加文档。
		solrServer.add(document);
		solrServer.commit();
		// 4、返回成功，返回TaotaoResult。
		return TaotaoResult.ok();
	}

	
}
