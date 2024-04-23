package com.system.xysmartassistants.controller.system;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.UserManagement;
import com.system.xysmartassistants.service.UserManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 郝娜
 * 2024年4月22日10点28分
 *
 * 用户信息管理控制层
 */
@RestController
@RequestMapping("/UserInformation")
@Api(tags = "用户信息相关接口", value = "UserInformationController")
public class UserInformationController {

    @Resource
    UserManagementService userManagementService;

    private final Logger logger = LoggerFactory.getLogger(UserInformationController.class);
// TODO: 2024/4/22 2. 这里要实现基础用户的增删改查
// TODO: 2024/4/22 3. 启用停用用户需要判断当前登录用户是否为管理员从redis中获取，若未获取到返回登录超时
// TODO: 2024/4/22 4. 登录逻辑要将前端提交的代码进行MD5加密，并且保存在redis中并设置过期时间

    /**
     * 用户登录接口
     *
     * @param userManagement
     * @return
     */
    @PostMapping(value = "/userLogin")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public ResultBean userLogin(UserManagement userManagement){
        Assert.notNull(userManagement, "userManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("用户登录接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 用户退出接口
     *
     * @param userManagement
     * @return
     */
    @PostMapping(value = "/userLogout")
    @ApiOperation(value = "用户退出接口", notes = "用户退出接口")
    public ResultBean userLogout(UserManagement userManagement){
        Assert.notNull(userManagement, "userManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("用户退出接口：");
        ResultBean resultBean = null;
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 获取用户信息(包含登录状态)
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/queryUserMessage")
    @ApiOperation(value = "获取用户信息(包含登录状态)", notes = "获取用户信息(包含登录状态)")
    public ResultBean queryUserMessage(String username){
        Assert.notNull(username, "username不可为空！");
        logger.info("====================调用接口====================");
        logger.info("获取用户信息：");
        ResultBean resultBean = userManagementService.selectUserMessage(username);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 注册新用户
     *
     * @param userManagement
     * @return
     */
    @PostMapping(value = "/RegisterUser")
    @ApiOperation(value = "注册新用户", notes = "注册新用户")
    public ResultBean registerUser(UserManagement userManagement){
        Assert.notNull(userManagement, "userManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("注册新用户：");
        ResultBean resultBean = userManagementService.insert(userManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 修改用户信息(包含启用/停用/删除)
     *
     * @param userManagement
     * @return
     */
    @PostMapping(value = "/editUser")
    @ApiOperation(value = "修改用户信息(包含启用/停用/删除)", notes = "修改用户信息(包含启用/停用/删除)")
    public ResultBean editUser(UserManagement userManagement){
        Assert.notNull(userManagement, "userManagement不可为空！");
        logger.info("====================调用接口====================");
        logger.info("修改用户信息：");
        ResultBean resultBean = userManagementService.edit(userManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }

}
