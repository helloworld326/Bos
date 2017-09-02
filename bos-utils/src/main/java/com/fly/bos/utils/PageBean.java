package com.fly.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
	private int currentPage; // 当前页数
	private int total; // 总记录数
	private int pageSize; // 每页显示记录数
	private DetachedCriteria criteria; // 离线查询
	private List rows; // 数据集合

	public PageBean() {
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public DetachedCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", total=" + total + ", pageSize=" + pageSize + ", criteria="
				+ criteria + ", rows=" + rows + "]";
	}

}
