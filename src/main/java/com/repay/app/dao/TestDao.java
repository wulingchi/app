package com.repay.app.dao;

import com.repay.app.dao.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestDao {

    Test getTest(@Param("id") Long id);

}
