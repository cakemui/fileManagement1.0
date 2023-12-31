package com.system.xysmartassistants.common;

import com.system.xysmartassistants.constant.ResultConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 小月
 * 2023.9.3.16点29分
 *
 * 返回值类
 */
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
        code = ResultConstant.USER_ERROR_MSG_CODE;
        smg = "返回值未被赋值";
    }
}
