package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.taotao.result.SearchResult;
import com.taotao.search.pojo.SearchItem;

@Component
public class SearchDao {

	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query) throws Exception {
		// TODO Auto-generated method stub

		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList solrDocumentList = response.getResults();
		List<SearchItem> itemList = new ArrayList<>();
		
		for (SolrDocument solrDocument : solrDocumentList) {

			SearchItem item = new SearchItem();
			item.setId((String)solrDocument.get("id"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));

			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = "";
			//有高亮显示的内容时。
			if (list != null && list.size() > 0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) solrDocument.get("item_title");
			}
			item.setTitle(itemTitle);
			//添加到商品列表
			itemList.add(item);
		}
		SearchResult result = new SearchResult();
		result.setItemList(itemList);
		result.setRecordCount(solrDocumentList.getNumFound());
		return result;
	}


	
}
