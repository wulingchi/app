package com.repay.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class OrderListDto extends BaseDto{
	
	
	@ApiModelProperty(value="用户id或手机号",name ="name")
	private String name;
	
	@ApiModelProperty(value="'类型（1.众筹/2.信用贷/3.房贷）",name ="type")
	private Integer type;
	
	@ApiModelProperty(value="开始时间",name ="startTime")
	private String startTime;
	
	@ApiModelProperty(value="结束时间",name ="endTime")
	private String endTime;
	
	@ApiModelProperty(value="状态（0.待确定/1.待支付/2.已完成/3.作废）",name ="status")
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
