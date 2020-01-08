package com.repay.app.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AppOrderActual {
    /**
     * id
     */
    private Integer id;

    /**
     * 关联订单id
     */
    private Integer order_id;

    /**
     * 付款人
     */
    private Integer pay_user_id;

    /**
     * 收款人
     */
    private Integer received_id;

    /**
     * 费用
     */
    private BigDecimal fee;

    /**
     * 状态（1.付款待确定/2.已确定）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 确认收款时间
     */
    private Date confrim_time;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联订单id
     * @return order_id 关联订单id
     */
    public Integer getOrder_id() {
        return order_id;
    }

    /**
     * 关联订单id
     * @param order_id 关联订单id
     */
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    /**
     * 付款人
     * @return pay_user_id 付款人
     */
    public Integer getPay_user_id() {
        return pay_user_id;
    }

    /**
     * 付款人
     * @param pay_user_id 付款人
     */
    public void setPay_user_id(Integer pay_user_id) {
        this.pay_user_id = pay_user_id;
    }

    /**
     * 收款人
     * @return received_id 收款人
     */
    public Integer getReceived_id() {
        return received_id;
    }

    /**
     * 收款人
     * @param received_id 收款人
     */
    public void setReceived_id(Integer received_id) {
        this.received_id = received_id;
    }

    /**
     * 费用
     * @return fee 费用
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 费用
     * @param fee 费用
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 状态（1.付款待确定/2.已确定）
     * @return status 状态（1.付款待确定/2.已确定）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（1.付款待确定/2.已确定）
     * @param status 状态（1.付款待确定/2.已确定）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 创建时间
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 确认收款时间
     * @return confrim_time 确认收款时间
     */
    public Date getConfrim_time() {
        return confrim_time;
    }

    /**
     * 确认收款时间
     * @param confrim_time 确认收款时间
     */
    public void setConfrim_time(Date confrim_time) {
        this.confrim_time = confrim_time;
    }
}