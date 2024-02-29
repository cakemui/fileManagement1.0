package com.system.xysmartassistants.common;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 小月
 * 2024.2.29.17点08分
 *
 * 基础类型
 */
@Data
@ApiModel(value = "基础类型")
public class BaseEntity {

    @ApiModelProperty("当前第几页")
    @TableField(exist = false)
    private int page;

    @ApiModelProperty("每页显示多少条数据")
    @TableField(exist = false)
    private int pageSize;

    public void fillPageInfo(int page, int pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }
}
