package com.repay.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.ImageInfo;

@Mapper
public interface ImageInfoMapper {
    int deleteByPrimaryKey(Integer img_id);

    int insert(ImageInfo record);

    int insertSelective(ImageInfo record);

    ImageInfo selectByPrimaryKey(Integer img_id);

    int updateByPrimaryKeySelective(ImageInfo record);

    int updateByPrimaryKey(ImageInfo record);
}