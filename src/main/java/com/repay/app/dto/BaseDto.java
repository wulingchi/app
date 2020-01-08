package com.repay.app.dto;



public class BaseDto {
	//当前页
	private Integer pageNum = 1;
	
	
	static int COUNT = 10;
	
	
	private Integer startRow;

	// 每页数量
	private Integer pageSize = COUNT;
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
}
