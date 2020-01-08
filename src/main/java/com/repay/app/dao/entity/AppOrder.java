package com.repay.app.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AppOrder {
    /**
     * id
     */
    private Integer id;

    /**
     * 类型（1.众筹/2.信用贷/3.房贷）
     */
    private Byte type;

    /**
     * 众筹总金额
     */
    private BigDecimal fee;

    /**
     * 已收金额
     */
    private BigDecimal received;

    /**
     * 欠款证明说明
     */
    private String prove;

    /**
     * 状态（0.待确定/1.待支付/2.已完成/3.作废）
     */
    private Byte status;

    /**
     * 创建人
     */
    private Integer creater_id;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改人
     */
    private Integer updater_id;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 订单号（标识）
     */
    private String order_no;
    
    /**
     * 图片地址
     */
	private String img_url;
	
	 /**
     * 图片大小
     */
	private Long img_size;

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
     * 类型（1.众筹/2.信用贷/3.房贷）
     * @return type 类型（1.众筹/2.信用贷/3.房贷）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 类型（1.众筹/2.信用贷/3.房贷）
     * @param type 类型（1.众筹/2.信用贷/3.房贷）
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 众筹总金额
     * @return fee 众筹总金额
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 众筹总金额
     * @param fee 众筹总金额
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 已收金额
     * @return received 已收金额
     */
    public BigDecimal getReceived() {
        return received;
    }

    /**
     * 已收金额
     * @param received 已收金额
     */
    public void setReceived(BigDecimal received) {
        this.received = received;
    }

    /**
     * 欠款证明说明
     * @return prove 欠款证明说明
     */
    public String getProve() {
        return prove;
    }

    /**
     * 欠款证明说明
     * @param prove 欠款证明说明
     */
    public void setProve(String prove) {
        this.prove = prove == null ? null : prove.trim();
    }

    /**
     * 状态（0.待确定/1.待支付/2.已完成/3.作废）
     * @return status 状态（0.待确定/1.待支付/2.已完成/3.作废）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态（0.待确定/1.待支付/2.已完成/3.作废）
     * @param status 状态（0.待确定/1.待支付/2.已完成/3.作废）
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 创建人
     * @return creater_id 创建人
     */
    public Integer getCreater_id() {
        return creater_id;
    }

    /**
     * 创建人
     * @param creater_id 创建人
     */
    public void setCreater_id(Integer creater_id) {
        this.creater_id = creater_id;
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
     * 修改人
     * @return updater_id 修改人
     */
    public Integer getUpdater_id() {
        return updater_id;
    }

    /**
     * 修改人
     * @param updater_id 修改人
     */
    public void setUpdater_id(Integer updater_id) {
        this.updater_id = updater_id;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 修改时间
     * @param update_time 修改时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * 订单号（标识）
     * @return order_no 订单号（标识）
     */
    public String getOrder_no() {
		return order_no;
	}
    

    /**
     * 订单号（标识）
     * @param order_no 订单号（标识）
     */
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	
	public String getImg_url() {
		return img_url;
	}
	
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public Long getImg_size() {
		return img_size;
	}

	public void setImg_size(Long img_size) {
		this.img_size = img_size;
	}
}