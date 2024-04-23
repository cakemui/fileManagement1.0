package com.system.xysmartassistants.controller.system;

import com.system.xysmartassistants.common.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO: 2024/4/22 保险客户新增、修改接口入参为客户对象,要划分保险人和投保人

    /**
     * 保险客户新增接口
     *
     * @param client
     * @return
     */
    @PostMapping(value = "/insertClient")
    @ApiOperation(value = "保险客户新增接口", notes = "保险客户新增接口")
    public ResultBean insertClient(String client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户新增接口：");
        ResultBean resultBean = null;
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
    public ResultBean editClient(String client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户修改/删除接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 保险客户分页查询接口
     *
     * @param client
     * @return
     */
    @PostMapping(value = "/queryClient")
    @ApiOperation(value = "保险客户查询接口", notes = "保险客户查询接口")
    public ResultBean queryClient(String client){
        Assert.notNull(client, "client不可为空！");
        logger.info("====================调用接口====================");
        logger.info("保险客户查询接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }
}
