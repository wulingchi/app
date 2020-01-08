package com.repay.app.dao.entity;

public class AppImageRelation {
    /**
     * id
     */
    private Integer relation_id;

    /**
     * 图片id
     */
    private Integer img_id;

    /**
     * 业务id外键（用户id/订单id）
     */
    private Integer bus_id;

    /**
     * 类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码）
     */
    private Byte type;

    /**
     * id
     * @return relation_id id
     */
    public Integer getRelation_id() {
        return relation_id;
    }

    /**
     * id
     * @param relation_id id
     */
    public void setRelation_id(Integer relation_id) {
        this.relation_id = relation_id;
    }

    /**
     * 图片id
     * @return img_id 图片id
     */
    public Integer getImg_id() {
        return img_id;
    }

    /**
     * 图片id
     * @param img_id 图片id
     */
    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }

    /**
     * 业务id外键（用户id/订单id）
     * @return bus_id 业务id外键（用户id/订单id）
     */
    public Integer getBus_id() {
        return bus_id;
    }

    /**
     * 业务id外键（用户id/订单id）
     * @param bus_id 业务id外键（用户id/订单id）
     */
    public void setBus_id(Integer bus_id) {
        this.bus_id = bus_id;
    }

    /**
     * 类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码）
     * @return type 类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码）
     * @param type 类型（0.其他/1.身份证A/2.身份证B/3.微信收款码/4.支付宝收款码）
     */
    public void setType(Byte type) {
        this.type = type;
    }
}