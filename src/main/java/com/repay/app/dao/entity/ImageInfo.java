package com.repay.app.dao.entity;

import java.util.Date;

public class ImageInfo {
    /**
     * 图片id
     */
    private Integer img_id;

    /**
     * 图片地址
     */
    private String img_url;

    /**
     * 图片大小（单位字节）
     */
    private Long size;

    /**
     * 创建人id
     */
    private Integer create_id;

    /**
     * 创建时间
     */
    private Date create_time;

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
     * 图片地址
     * @return img_url 图片地址
     */
    public String getImg_url() {
        return img_url;
    }

    /**
     * 图片地址
     * @param img_url 图片地址
     */
    public void setImg_url(String img_url) {
        this.img_url = img_url == null ? null : img_url.trim();
    }

    /**
     * 图片大小（单位字节）
     * @return size 图片大小（单位字节）
     */
    public Long getSize() {
        return size;
    }

    /**
     * 图片大小（单位字节）
     * @param size 图片大小（单位字节）
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 创建人id
     * @return create_id 创建人id
     */
    public Integer getCreate_id() {
        return create_id;
    }

    /**
     * 创建人id
     * @param create_id 创建人id
     */
    public void setCreate_id(Integer create_id) {
        this.create_id = create_id;
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
}