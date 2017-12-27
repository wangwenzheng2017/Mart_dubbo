package com.taotao.result;

import java.io.Serializable;
import java.util.List;

import com.taotao.search.pojo.SearchItem;

public class SearchResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3296237330632649745L;

	private List<SearchItem> itemList;
	private long recordCount;
	private long pageCount;
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
