package com.repay.app.controller.base;


/**
 * Service 全局错误码
 */
public enum ErrorCode {

    SYS_AUTH_OVERDUE("-100", "验证码已过期"),
    SYS_AUTH_SIGN_ERROR("-101", "签名无效！"),
    SYS_BUSY_ERROR("503","调用太忙了,请休息下"),
    SYS_AUTH_ERROR("-99", "验证码错误"),
    SYS_ERROR("-1", "系统繁忙"),
    SUCCESS("0", "success"),// 这里必须为0，切勿修改次参数
    LOG_ERROR("1000", "新增日志错误"),
    DATASORCE("1001", "无法匹配数据源"),
    BUS_NUll("0", "查询无结果"),// 这里必须为0，切勿修改次参数
    DUBBO_WHITELIST("1", "无法连接远程服务"),// dubbo连接异常 远程服务异常
    SYS_TOKEN("401", "未授权的登录"),
    SYS_TOKEN_LOGIN("402", "重新登录"),
    WECHAT_SESSION_EFFICACY("403", "微信sessionKey失效"),
    NO_CASE("600", "联系管理员初始化数据"),
    BUS_UPDATE_ERROR("1003", "修改失败"),
    BUS_ADD_ERROR("1004", "新增失败"),
    BUS_ERROR("1006", "逻辑异常"),
    DATA_VALIDATE("1007", "数据校验不通过"),
    BUS_DEL_ERROR("1008", "删除失败"),
    NEED_CODE("1010", "登陆失败超过次数，下次登陆需要验证码！"),
    REFRESH_TOKEN("9999", "刷新token"),
    RSA_INIT("2000", "初始化密钥对错误"),
    RSA_ENCRYPT("2001", "加密错误"),
    RSA_DECRYPT("2002", "解密错误"),
    EXCEL_VALIDATE("2004", "Excel校验失败,请下载错误信息"),;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private ErrorCode(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
