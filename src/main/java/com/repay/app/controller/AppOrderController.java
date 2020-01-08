package com.repay.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.repay.app.dao.entity.AppOrder;
import com.repay.app.dao.entity.AppUser;
import com.repay.app.dto.OrderListDto;
import com.repay.app.service.AppOrderService;
import com.repay.app.service.AppUserService;
import com.repay.app.util.GsonUtil;
import com.repay.app.util.ResultObject;
import com.repay.app.vo.OrderListVo;

@Api(value = "订单操作",description="订单操作")
@RestController
@RequestMapping("/v1/order")
public class AppOrderController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppOrderController.class);
	
	@Autowired
	private AppOrderService appOrderService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
    private JedisPool jedisPool;
	
	
	
	@ApiOperation(value = "订单申请", httpMethod = "POST", response = AppOrder.class, notes = "订单申请")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "token值", required = true, dataType = "String", paramType = "header")
	   })
	@RequestMapping(value="/add" ,method = RequestMethod.POST)
	public ResultObject addOrder(HttpServletRequest request,@RequestBody AppOrder dto ){
		ResultObject ro = ResultObject.getSuccessResult("获取成功");
		try{
			//这里后续改  目前重新查表
			String token = request.getHeader("token");
			//JSON获取用户数据
			Jedis jedis = jedisPool.getResource();
			String json = jedis.get(token);
			AppUser user = GsonUtil.GsonToBean(json, AppUser.class);
			dto.setCreater_id(user.getUser_id());
			
			ro = appOrderService.addOrder(dto);
		}catch(Exception e){
			LOGGER.error("订单申请异常", e);
			return ResultObject.getFailResult("订单申请失败");
		}
		return ro;
	}
	
	@ApiOperation(value = "订单列表", httpMethod = "POST", response = OrderListVo.class, notes = "订单列表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "token值", required = true, dataType = "String", paramType = "header")
	   })
	@RequestMapping(value="/list" ,method = RequestMethod.POST)
	public ResultObject orderList(HttpServletRequest request,@RequestBody OrderListDto dto){
		ResultObject ro = ResultObject.getSuccessResult("订单列表获取成功");
		try{
			ro  =  appOrderService.getOrderList(dto);
		}catch(Exception e){
			LOGGER.error("订单列表获取成功 异常", e);
			return ResultObject.getFailResult("订单列表获取成功 失败");
		}
		return ro;
	}
	
	
	public ResultObject orderDetailById(){
		return null;
	}
	

}
