package com.repay.app.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repay.app.dao.AppImageRelationMapper;
import com.repay.app.dao.AppOrderMapper;
import com.repay.app.dao.ImageInfoMapper;
import com.repay.app.dao.entity.AppImageRelation;
import com.repay.app.dao.entity.AppOrder;
import com.repay.app.dao.entity.ImageInfo;
import com.repay.app.dto.OrderListDto;
import com.repay.app.service.AppOrderService;
import com.repay.app.util.Help;
import com.repay.app.util.ResultObject;
import com.repay.app.util.ResultPage;
import com.repay.app.vo.OrderListVo;
@Service
public class AppOrderServiceImpl implements AppOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppOrderServiceImpl.class);
	
	@Autowired
	private AppOrderMapper appOrderMapper;
	
	@Autowired
	private ImageInfoMapper imageInfoMapper;
	
	@Autowired
	private AppImageRelationMapper appImageRelationMapper;

	@Override
	public ResultObject addOrder(AppOrder dto) {
		ResultObject ro = ResultObject.getSuccessResult("订单申请成功");
		try{
			//1.新增订单
			dto.setCreate_time(new Date());
			//获取订单号（标识）C/x/F+14*X
			String orderNO = getOrderNO(dto.getType());
			dto.setOrder_no(orderNO);
			int count = appOrderMapper.insertSelective(dto);
			if(count>0){
				Integer bus_id = dto.getId();
				//2.根据类型存入上传文件信息
				ImageInfo image = new ImageInfo();
				image.setCreate_id(dto.getCreater_id());
				image.setCreate_time(dto.getCreate_time());
				image.setImg_url(dto.getImg_url());
				image.setSize(dto.getImg_size());
				int im =imageInfoMapper.insertSelective(image);
				if(im>0){
					Byte type = 5;
					//类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码/5.众筹/6.信用贷/7.房贷）
					Integer img_id = image.getImg_id();
					AppImageRelation record = new AppImageRelation();
					record.setBus_id(bus_id);
					record.setImg_id(img_id);
					switch(dto.getType()){
					case 1:
						type = 5;
						break;
					case 2:
						type = 6;
						break;
					case 3:
						type = 7;
						break;
					}
					record.setType(type);
					appImageRelationMapper.insert(record);
				}else{
					ro =ResultObject.getFailResult("新增文件信息失败");
				}
			}else{
				ro =ResultObject.getFailResult("订单申请失败");
			}
		}catch(Exception e){
			LOGGER.error("订单申请异常", e);
			ro =ResultObject.getFailResult("订单申请异常");
		}
		return ro;
	}

	private String getOrderNO(Byte type) {
		//规则 标识前缀 +14日期数
		StringBuffer str = new StringBuffer();
		switch(type){
			case 1:
				str.append("C");
				break;
			case 2:
				str.append("X");
				break;
			case 3:
				str.append("F");
				break;
			default:
				str.append("ER");
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String dateNameStr = sdf.format(now);
		str.append(dateNameStr);
		return str.toString();
	}

	@Override
	public ResultObject getOrderList(OrderListDto dto) {
		ResultObject ro = ResultObject.getSuccessResult("订单列表获取成功");
		try{
			int count  = appOrderMapper.getOrderListCount(dto);
			ResultPage resultPage = new ResultPage(count,dto.getPageNum());
			dto.setStartRow(resultPage.getStartRow());
			dto.setPageSize(resultPage.getPageSize());
			List<OrderListVo> list = appOrderMapper.getOrderList(dto);
			//修饰list
			decorateOrderList(list);
			resultPage.setPageData(list);
			resultPage.setDataCount(count);
			ro.setData(resultPage);
		}catch(Exception e){
			LOGGER.error("订单列表获取 异常", e);
			ro =ResultObject.getFailResult("订单列表获取 失败");
		}
		return ro;
	}

	private void decorateOrderList(List<OrderListVo> list) {
		if(Help.isNotNull(list)){
			for (OrderListVo v : list) {
				BigDecimal rate = v.getReceived().divide(v.getFee(),2,BigDecimal.ROUND_HALF_UP);
				if(Help.isNotNull(rate)){
					String feeRate = rate.multiply(new BigDecimal(100)).toString();
					v.setFeeRate(feeRate+"%");
				}else{
					v.setFeeRate("0%");
				}
				
				
			}
		}
	}

}
