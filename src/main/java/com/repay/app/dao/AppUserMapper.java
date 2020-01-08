package com.repay.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.AppUser;
import com.repay.app.dto.UserDto;
import com.repay.app.vo.UserListVo;

@Mapper
public interface AppUserMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Integer user_id);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

	AppUser getUserByAccount(String account);

	int getUserExistByAccount(String account);

	int getListCount(UserDto dto);

	List<UserListVo> getList(UserDto dto);
}