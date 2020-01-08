package com.repay.app.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserListVo {
	
	@ApiModelProperty(value="姓名",name ="name")
	private String name;
	
	@ApiModelProperty(value="电话",name ="phone")
	private String phone;
	
	@ApiModelProperty(value="时间",name ="createTime")
	private String createTime;
	
	@ApiModelProperty(value="是否激活",name ="isActive")
	private String isActive;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
