package com.repay.app.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.repay.app.dao.entity.AppUser;
import com.repay.app.util.GsonUtil;
import com.repay.app.util.Help;

@Service
public class SessionService {
	
	
	@Autowired
    private JedisPool jedisPool;
	
	
	//保存token
	public String saveToken(AppUser user){
		String account = user.getAccount();
		
		user.setPassword(null);
		//user转Json
		String strUser = GsonUtil.GsonString(user);	
		System.err.println(strUser);
		
		String s = UUID.randomUUID().toString();
		String token = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24)+"#"+account;
		try(Jedis jedis = jedisPool.getResource()){
			jedis.setex(token, 3600*24*60,strUser);
		}
		return token;
	}
	
	//判断是否存在token
	public boolean isTokenValid(String token){
		if(Help.isNull(token)){
			return false;
		}
		boolean flag = false;
		try(Jedis jedis = jedisPool.getResource()){
			flag = jedis.exists(token);
		}
		return flag;
	}
	
	//销毁token
	public synchronized void destoryToken(String token){
		if(Help.isNotNull(token)){
			try(Jedis jedis = jedisPool.getResource()){
				jedis.del(token);
			}
		}
	}

}
