package com.repay.app.service;

import com.repay.app.dao.entity.AppOrder;
import com.repay.app.dto.OrderListDto;
import com.repay.app.util.ResultObject;

public interface AppOrderService {

	ResultObject addOrder(AppOrder dto);

	ResultObject getOrderList(OrderListDto dto);
	

}
