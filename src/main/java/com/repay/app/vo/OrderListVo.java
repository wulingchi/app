package com.repay.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OrderListVo {
	
	@ApiModelProperty(value="id",name ="id")
	private Integer id;
	
	@ApiModelProperty(value="用户ID",name ="userId")
	private String userId;
	
	@ApiModelProperty(value="用户名称",name ="userName")
	private String userName;
	
	@ApiModelProperty(value="用户手机号",name ="phone")
	private String phone;
	
	@ApiModelProperty(value="众筹款金额",name ="fee")
	private BigDecimal fee;
	
	@ApiModelProperty(value="类型（1.众筹/2.信用贷/3.房贷）",name ="type")
	private byte type;
	
	@ApiModelProperty(value="创建众筹时间",name ="createTime")
	private Date createTime;
	
	@ApiModelProperty(value="待付手续金额",name ="feeToPay")
	private BigDecimal feeToPay;
	
	@ApiModelProperty(value="已众筹金额",name ="received")
	private BigDecimal received;
	
	@ApiModelProperty(value="最后众筹收款时间",name ="updateTime")
	private Date updateTime;
	
	@ApiModelProperty(value="占比例",name ="feeRate")
	private String feeRate;
	
	@ApiModelProperty(value="状态（0.待确定/1.待支付/2.已完成/3.作废）",name ="status")
	private byte status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getFeeToPay() {
		return feeToPay;
	}

	public void setFeeToPay(BigDecimal feeToPay) {
		this.feeToPay = feeToPay;
	}

	public BigDecimal getReceived() {
		return received;
	}

	public void setReceived(BigDecimal received) {
		this.received = received;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	

}
