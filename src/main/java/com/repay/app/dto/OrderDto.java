package com.repay.app.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OrderDto {
	
	@ApiModelProperty(value="订单ID",name ="id")
	private Integer id;
	
	@ApiModelProperty(value="类型（1.众筹/2.信用贷/3.房贷）",name ="type")
    private Byte type;

	@ApiModelProperty(value="众筹总金额",name ="fee")
    private BigDecimal fee;

	@ApiModelProperty(value="已收金额",name ="received")
    private BigDecimal received = new BigDecimal(0);

	@ApiModelProperty(value="欠款证明说明",name ="prove")
    private String prove;

	@ApiModelProperty(value="状态（0.待确定/1.待支付/2.已完成/3.作废）",name ="status")
    private Byte status;

	@ApiModelProperty(value="创建人",name ="creater_id")
    private Integer creater_id;

	@ApiModelProperty(value="创建时间",name ="create_time")
    private Date create_time;

	@ApiModelProperty(value="修改人",name ="updater_id")
    private Integer updater_id;

	@ApiModelProperty(value="修改时间",name ="update_time")
    private Date update_time;

	@ApiModelProperty(value="订单号（标识）",name ="order_no")
    private byte[] order_no;
	

	
	

}
