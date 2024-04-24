package com.system.xysmartassistants.controller.system;


import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.dao.system.InsurancePolicyManagementDao;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.InsurancePolicyManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 郝娜
 * 2024年4月22日14点50分
 *
 * 保险业务控制层
 */
@RestController
@RequestMapping("/InsuranceManagementController")
@Api(tags = "保险业务相关接口", value = "InsuranceManagementController")
public class InsuranceManagementController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceManagementController.class);

    @Resource
    InsurancePolicyManagementService insurancePolicyManagementService;

    /**
     * 客户保险产品绑定接口(新增保单)
     *
     * @param policyManagement
     * @return
     */
    @PostMapping(value = "/insertPolicy")
    @ApiOperation(value = "客户保险产品绑定接口(新增保单)", notes = "客户保险产品绑定接口(新增保单)")
    public ResultBean insertPolicy(InsurancePolicyManagement policyManagement){
        Assert.notNull(policyManagement, "policyManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("客户保险产品绑定接口：");
        ResultBean resultBean = insurancePolicyManagementService.insert(policyManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 修改/删除保单信息接口
     *
     * @param policyManagement
     * @return
     */
    @PostMapping(value = "/editPolicy")
    @ApiOperation(value = "修改/删除保单信息接口", notes = "修改/删除保单信息接口")
    public ResultBean editPolicy(InsurancePolicyManagement policyManagement){
        Assert.notNull(policyManagement, "policyManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("修改/删除保单信息接口：");
        ResultBean resultBean = insurancePolicyManagementService.edit(policyManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     *分页查询保单信息接口
     *
     * @param policyManagement
     * @return
     */
    @PostMapping(value = "/queryPoliciesByPage")
    @ApiOperation(value = "分页查询保单信息接口", notes = "分页查询保单信息接口", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultBean<PageInfo<InsurancePolicyManagement>> queryPoliciesByPage(@RequestBody InsurancePolicyManagement policyManagement){
        logger.info("====================调用接口====================");
        logger.info("分页查询保单接口：");
        ResultBean resultBean = insurancePolicyManagementService.selectClientByPage(policyManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }

}
