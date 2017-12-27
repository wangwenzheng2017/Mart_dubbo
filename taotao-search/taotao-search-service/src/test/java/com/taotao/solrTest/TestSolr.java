package com.taotao.solrTest;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolr {
	
	/**
	 * 单机solr
	 * @throws Exception
	 *//*
	@Test
	public void addDocument() throws Exception {
		String url = "http://192.168.8.130:9080/solr";
		SolrServer solrServer = new HttpSolrServer(url);
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id","003");
		document.addField("item_title","sd5466as");
		document.addField("item_price","1113");
		solrServer.add(document);
		solrServer.commit();
//		System.out.println(java.net.URLEncoder.encode("Hello World", "UTF-8"));
	}*/
	/**
	 * 集群solrCloud
	 * @throws Exception
	 */
	/*@Test
	public void addDocuments() throws Exception {
		String url = "192.168.8.130:2181,192.168.8.130:2182,192.168.8.130:2183";
		CloudSolrServer solrServer = new CloudSolrServer(url);
		solrServer.setDefaultCollection("collection2");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id","007");
		document.addField("item_title","qqqqqwwww");
		document.addField("item_price","9999");
		solrServer.add(document);
		solrServer.commit();
//		System.out.println(java.net.URLEncoder.encode("Hello World", "UTF-8"));
	}*/
	
	/*@Test
	public void deleteDocumentById() throws Exception {
		String url = "http://192.168.8.130:9080/solr";
		SolrServer solrServer = new HttpSolrServer(url);
		solrServer.deleteById("001");
		solrServer.commit();
	}
	@Test
	public void deleteDocumentByQuery() throws Exception {
		String url = "http://192.168.8.130:9080/solr";
		SolrServer solrServer = new HttpSolrServer(url);
		solrServer.deleteByQuery("item_title:测试aaas");
		solrServer.commit();
	}
	@Test
	public void queryDocument() throws Exception {
		String url = "http://192.168.8.130:9080/solr";
		SolrServer solrServer = new HttpSolrServer(url);
		SolrQuery query = new SolrQuery();
		
		query.setQuery("*:*");
		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList list = response.getResults();
		System.out.println("查询结果的总记录数："+list.getNumFound());
		for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}
	@Test
	public void queryDocumentWithHightLighting() throws Exception {
		String url = "http://192.168.8.130:9080/solr";
		SolrServer solrServer = new HttpSolrServer(url);
		SolrQuery query = new SolrQuery();
		
		query.setQuery("测试");
		query.set("df","item_keywords");
		query.setHighlight(true);
		
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		
		
		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList list = response.getResults();
		System.out.println("查询结果的总记录数："+list.getNumFound());
		for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			Map<String,Map<String,List<String>>> highLighting = response.getHighlighting();
			List<String> list11 = highLighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = null;
			if (list11 != null && list11.size() > 0) {
				itemTitle = list11.get(0);
			}else{
				itemTitle = (String) solrDocument.get("item_title");
			}
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}*/
}
