package com.system.xysmartassistants.controller.system;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import com.system.xysmartassistants.domain.entity.InsuranceProductManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.InsuranceProductManagementService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

public class InsuranceProduceManagementController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceProduceManagementController.class);

    @Resource
    InsuranceProductManagementService insuranceProductManagementService;

    /**
     * 保险产品新增接口
     *
     * @param insuranceProduct
     * @return
     */
    @PostMapping(value = "/insertProduct")
    @ApiOperation(value = "保险产品新增接口", notes = "保险产品新增接口")
    public ResultBean insertInsurance(InsuranceProductManagement insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险产品新增接口：");
        ResultBean resultBean = insuranceProductManagementService.insert(insuranceProduct);
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
    public ResultBean editInsurance(InsuranceProductManagement insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险产品修改/删除接口：");
        ResultBean resultBean = insuranceProductManagementService.edit(insuranceProduct);
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
    public ResultBean<PageInfo<InsuranceProductManagement>> queryInsurance(InsuranceProductManagement insuranceProduct){
        Assert.notNull(insuranceProduct, "insuranceProduct不可为空！");
        logger.info("====================调用接口====================");
        logger.info("分页查询保险产品查询接口：");
        ResultBean resultBean = insuranceProductManagementService.selectProductByPage(insuranceProduct);
        logger.info("====================调用结束====================");
        return resultBean;
    }
}
