package com.repay.app.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repay.app.dao.AppUserMapper;
import com.repay.app.dao.entity.AppUser;
import com.repay.app.dto.UserDto;
import com.repay.app.service.AppUserService;
import com.repay.app.service.SessionService;
import com.repay.app.util.MD5;
import com.repay.app.util.ResultObject;
import com.repay.app.util.ResultPage;
import com.repay.app.vo.UserListVo;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	
	@Autowired
	private AppUserMapper appUserMapper;
	
	@Autowired
	private SessionService sessionService;

	@Override
	public AppUser getUserByAccount(String account) {
		return appUserMapper.getUserByAccount(account);
	}

	@Override
	public ResultObject addUserInfo(AppUser user) {
		ResultObject ro = ResultObject.getSuccessResult("注册成功");
		try{
			//1.判断账号时候重复
			int count = appUserMapper.getUserExistByAccount(user.getAccount());
			if(count>0){
				ro = ResultObject.getFailResult("用户已存在");
			}else{
				//2.密码MD5加密
				String mdpw = MD5.toMD5(user.getPassword());
				user.setPassword(mdpw);
				
				//3.新增			
				int add = appUserMapper.insertSelective(user);
				if(add!=1){
					ro  = ResultObject.getFailResult("注册失败");
				}
			}
		}catch(Exception e){
			LOGGER.error("用户注册异常", e);
			return ResultObject.getFailResult("用户注册失败");
		}
		return ro;
	}

	@Override
	public ResultObject getList(UserDto dto) {
		ResultObject ro = ResultObject.getSuccessResult("获取成功");
		try{
			int count = appUserMapper.getListCount(dto);
			ResultPage resultPage = new ResultPage(count,dto.getPageNum());
			dto.setStartRow(resultPage.getStartRow());
			dto.setPageSize(resultPage.getPageSize());
			List<UserListVo> list  = appUserMapper.getList(dto);
			resultPage.setPageData(list);
			resultPage.setDataCount(count);
			ro.setData(resultPage);
		}catch(Exception e){
			LOGGER.error("获取异常", e);
			return ResultObject.getFailResult("获取失败");
		}
		return ro;
	}


}
