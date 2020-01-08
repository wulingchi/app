package com.repay.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "测试vod")
public class TestVo {


    @ApiModelProperty(notes = "自增主键")
    private Long id;
    @ApiModelProperty(notes = "名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
