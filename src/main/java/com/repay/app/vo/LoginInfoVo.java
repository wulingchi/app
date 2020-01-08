package com.repay.app.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录信息<p/>
 * 登录信息
 * @author XXZ
  */
@ApiModel(value="LoginInfo", description="登录信息")
public class LoginInfoVo {
	
	// 用户名，目前是工号
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户名称(登录名称)", required = true)	 	
	private String account;
	
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "密码", required = true)	     
	private String password;
	
    @JsonProperty(required = false)
    @ApiModelProperty(notes = "验证码", required = false)    
	private String captcha;
    
    @JsonProperty(required = false)
    @ApiModelProperty(notes = "邀请码", required = false)    
	private String invitecode;
    
    @JsonProperty(required = false)
    @ApiModelProperty(notes = "组件名称 agent_cloud,tfin .....", required = false)    

    private String componentName;
    

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

}
