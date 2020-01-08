package com.repay.app.dao.entity;

import java.util.Date;

public class AppUser {
    /**
     * 主键id
     */
    private Integer user_id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 邀请人，最上级为0
     */
    private Integer invite_id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String identity_no;

    /**
     * 性别 0、女 1、男
     */
    private Byte sex;

    /**
     * mac地址
     */
    private String mac;

    /**
     * 是否激活 0否 1是
     */
    private Byte active;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 主键id
     * @return user_id 主键id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 主键id
     * @param user_id 主键id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 账号
     * @return account 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 邀请人，最上级为0
     * @return invite_id 邀请人，最上级为0
     */
    public Integer getInvite_id() {
        return invite_id;
    }

    /**
     * 邀请人，最上级为0
     * @param invite_id 邀请人，最上级为0
     */
    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 联系电话
     * @return phone 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 联系电话
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 身份证号
     * @return identity_no 身份证号
     */
    public String getIdentity_no() {
        return identity_no;
    }

    /**
     * 身份证号
     * @param identity_no 身份证号
     */
    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no == null ? null : identity_no.trim();
    }

    /**
     * 性别 0、女 1、男
     * @return sex 性别 0、女 1、男
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 性别 0、女 1、男
     * @param sex 性别 0、女 1、男
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * mac地址
     * @return mac mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * mac地址
     * @param mac mac地址
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    /**
     * 是否激活 0否 1是
     * @return active 是否激活 0否 1是
     */
    public Byte getActive() {
        return active;
    }

    /**
     * 是否激活 0否 1是
     * @param active 是否激活 0否 1是
     */
    public void setActive(Byte active) {
        this.active = active;
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
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * 更新时间
     * @param update_time 更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}