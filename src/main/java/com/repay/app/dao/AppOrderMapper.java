package com.repay.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.repay.app.dao.entity.AppOrder;
import com.repay.app.dto.OrderListDto;
import com.repay.app.vo.OrderListVo;

@Mapper
public interface AppOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppOrder record);

    int insertSelective(AppOrder record);

    AppOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppOrder record);

    int updateByPrimaryKeyWithBLOBs(AppOrder record);

    int updateByPrimaryKey(AppOrder record);

	int getOrderListCount(OrderListDto dto);

	List<OrderListVo> getOrderList(OrderListDto dto);
}