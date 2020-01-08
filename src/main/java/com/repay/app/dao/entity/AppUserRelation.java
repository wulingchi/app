package com.repay.app.dao.entity;

import java.util.Date;

public class AppUserRelation {
    /**
     * 主键id
     */
    private Integer relation_id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 好友id
     */
    private Integer friend_id;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 主键id
     * @return relation_id 主键id
     */
    public Integer getRelation_id() {
        return relation_id;
    }

    /**
     * 主键id
     * @param relation_id 主键id
     */
    public void setRelation_id(Integer relation_id) {
        this.relation_id = relation_id;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 用户id
     * @param user_id 用户id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 好友id
     * @return friend_id 好友id
     */
    public Integer getFriend_id() {
        return friend_id;
    }

    /**
     * 好友id
     * @param friend_id 好友id
     */
    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
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