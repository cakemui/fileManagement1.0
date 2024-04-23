package com.system.xysmartassistants.controller.system;


import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.model.UserFileManagement;
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

    // TODO: 2024/4/22 新增、修改保单接口入参为保单对象

    /**
     * 客户保险产品绑定接口(新增保单)
     *
     * @param Policy
     * @return
     */
    @PostMapping(value = "/insertPolicy")
    @ApiOperation(value = "客户保险产品绑定接口(新增保单)", notes = "客户保险产品绑定接口(新增保单)")
    public ResultBean insertPolicy(String Policy){
        Assert.notNull(Policy, "Policy不可为空！");
        logger.info("====================调用接口====================");
        logger.info("客户保险产品绑定接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 修改/删除保单信息接口
     *
     * @param Policy
     * @return
     */
    @PostMapping(value = "/editPolicy")
    @ApiOperation(value = "修改/删除保单信息接口", notes = "修改/删除保单信息接口")
    public ResultBean editPolicy(String Policy){
        Assert.notNull(Policy, "Policy不可为空！");
        logger.info("====================调用接口====================");
        logger.info("修改/删除保单信息接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     *分页查询保单信息接口
     *
     * @param Policy
     * @return
     */
    @PostMapping(value = "/queryPoliciesByPage")
    @ApiOperation(value = "分页查询保单信息接口", notes = "分页查询保单信息接口", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultBean<PageInfo<UserFileManagement>> queryFilesByPage(@RequestBody String Policy){
        logger.info("====================调用接口====================");
        logger.info("分页查询已上传文件接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

}
