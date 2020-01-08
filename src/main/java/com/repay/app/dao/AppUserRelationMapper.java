package com.repay.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.AppUserRelation;

@Mapper
public interface AppUserRelationMapper {
    int deleteByPrimaryKey(Integer relation_id);

    int insert(AppUserRelation record);

    int insertSelective(AppUserRelation record);

    AppUserRelation selectByPrimaryKey(Integer relation_id);

    int updateByPrimaryKeySelective(AppUserRelation record);

    int updateByPrimaryKey(AppUserRelation record);
}