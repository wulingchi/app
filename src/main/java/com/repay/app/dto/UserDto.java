package com.repay.app.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDto extends BaseDto {
	
	@ApiModelProperty(value="姓名或电话",name ="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
