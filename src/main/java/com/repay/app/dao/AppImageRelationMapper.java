package com.repay.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.AppImageRelation;

@Mapper
public interface AppImageRelationMapper {
    int deleteByPrimaryKey(Integer relation_id);

    int insert(AppImageRelation record);

    int insertSelective(AppImageRelation record);

    AppImageRelation selectByPrimaryKey(Integer relation_id);

    int updateByPrimaryKeySelective(AppImageRelation record);

    int updateByPrimaryKey(AppImageRelation record);
}