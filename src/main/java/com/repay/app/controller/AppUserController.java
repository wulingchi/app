package com.repay.app.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.repay.app.dto.UserDto;
import com.repay.app.service.AppUserService;
import com.repay.app.util.ResultObject;
import com.repay.app.vo.LoginInfoVo;
import com.repay.app.vo.UserListVo;

@Api(value = "用户操作",description="用户操作")
@RestController
@RequestMapping("/v1/user")
public class AppUserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserController.class);
	
	@Autowired
	private AppUserService appUserService;
	
	
	@ApiOperation(value = "获取我的好友列表", httpMethod = "POST", response = UserListVo.class, notes = "获取我的好友列表")
	@RequestMapping(value="/list" ,method = RequestMethod.POST)
	public ResultObject list(HttpServletRequest request, @RequestBody UserDto dto){
		ResultObject ro = ResultObject.getSuccessResult("获取成功");
		try{
			ro  = appUserService.getList(dto);
		}catch(Exception e){
			LOGGER.error("服务器网络故障", e);
			return ResultObject.getFailResult("服务器网络故障");
		}
		return ro;
	}
	
	
	
	
	
	
	
	
	

}
