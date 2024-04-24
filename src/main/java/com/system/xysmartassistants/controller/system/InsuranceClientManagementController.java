package com.system.xysmartassistants.controller.system;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.InsuranceClientManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.InsuranceClientManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 郝娜
 * 2024年4月22日14点50分
 *
 * 保险客户管理控制层
 */
@RestController
@RequestMapping("/InsuranceClientManagementController")
@Api(tags = "保险客户管理相关接口", value = "InsuranceClientManagementController")
public class InsuranceClientManagementController {

    private final Logger logger = LoggerFactory.getLogger(InsuranceClientManagementController.class);

    @Resource
    InsuranceClientManagementService insuranceClientManagementService;

    /**
     * 保险客户新增接口
     *
     * @param client
     * @return
     */
    @PostMapping(value = "/insertClient")
    @ApiOperation(value = "保险客户新增接口", notes = "保险客户新增接口")
    public ResultBean insertClient(InsuranceClientManagement client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户新增接口：");
        ResultBean resultBean = insuranceClientManagementService.insert(client);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 保险客户修改/删除接口
     *
     * @param client
     * @return
     */
    @PostMapping(value = "/editClient")
    @ApiOperation(value = "保险客户修改/删除接口", notes = "保险客户修改/删除接口")
    public ResultBean editClient(InsuranceClientManagement client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户修改/删除接口：");
        ResultBean resultBean = insuranceClientManagementService.edit(client);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 保险客户分页查询接口
     *
     * @param client
     * @return
     */
    @PostMapping(value = "/queryClientByPage")
    @ApiOperation(value = "保险客户分页查询接口", notes = "保险客户分页查询接口")
    public ResultBean<PageInfo<InsuranceClientManagement>> queryClientByPage(InsuranceClientManagement client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户分页查询接口：");
        ResultBean<PageInfo<InsuranceClientManagement>> resultBean = insuranceClientManagementService.selectClientByPage(client);
        logger.info("====================调用结束====================");
        return resultBean;
    }
}
