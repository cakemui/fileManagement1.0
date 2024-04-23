package com.system.xysmartassistants.controller.system;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;

public class InsuranceProduceManagementController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceProduceManagementController.class);

    // TODO: 2024/4/22 保险产品新增、修改接口入参为保险产品对象

    /**
     * 保险产品新增接口
     *
     * @param insuranceProduct
     * @return
     */
    @PostMapping(value = "/insertProduct")
    @ApiOperation(value = "保险产品新增接口", notes = "保险产品新增接口")
    public ResultBean insertInsurance(String insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险产品新增接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 保险产品修改/删除接口
     *
     * @param insuranceProduct
     * @return
     */
    @PostMapping(value = "/editProduct")
    @ApiOperation(value = "保险产品修改/删除接口", notes = "保险产品修改/删除接口")
    public ResultBean editInsurance(String insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险产品修改/删除接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 分页查询保险产品查询接口(按销量排序)
     *
     * @param insuranceProduct
     * @return
     */
    @PostMapping(value = "/queryInsuranceProductByPage")
    @ApiOperation(value = "分页查询保险产品查询接口(按销量排序)", notes = "分页查询保险产品查询接口(按销量排序)")
    public ResultBean<PageInfo<UserFileManagement>> queryInsurance(String insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("分页查询保险产品查询接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }
}
