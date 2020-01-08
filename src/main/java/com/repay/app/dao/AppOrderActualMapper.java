package com.repay.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.AppOrderActual;

@Mapper
public interface AppOrderActualMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppOrderActual record);

    int insertSelective(AppOrderActual record);

    AppOrderActual selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppOrderActual record);

    int updateByPrimaryKey(AppOrderActual record);
}