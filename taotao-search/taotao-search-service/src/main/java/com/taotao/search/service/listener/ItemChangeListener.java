package com.taotao.search.service.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.result.TaotaoResult;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.service.impl.SearchItemServiceImpl;

public class ItemChangeListener implements MessageListener {

	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
//	@Autowired
//	private SearchItemServiceImpl searchItemServiceImpl;
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = null;
			Long itemId = null;
			
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
			}
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
//			searchItemServiceImpl.addDocument(itemId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
