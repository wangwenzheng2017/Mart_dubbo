package com.taotao.result;

import java.io.Serializable;
import java.util.List;

public class EasyUIResult implements Serializable {

	/**
	 * 11111 222222
	 */
	private static final long serialVersionUID = -287596517774651742L;

	private Integer total;

    private List<?> rows;

    public EasyUIResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
