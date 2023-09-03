package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回结果")
public class ResultBean<T> {

    @ApiModelProperty(value = "返回结果：200成功，500失败")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String smg;

    @ApiModelProperty(value = "返回数据条数")
    private int count;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResultBean (){
        code = 500;
        smg = "返回值未被赋值";
    }
}
