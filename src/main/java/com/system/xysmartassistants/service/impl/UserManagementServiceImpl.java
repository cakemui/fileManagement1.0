package com.system.xysmartassistants.service.impl;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.system.UserManagementDao;
import com.system.xysmartassistants.domain.entity.UserManagement;
import com.system.xysmartassistants.service.UserManagementService;
import com.system.xysmartassistants.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户管理表 服务实现类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Service("UserManagementService")
public class UserManagementServiceImpl implements UserManagementService {

    private final Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Resource
    UserManagementDao userManagementDao;

    @Resource
    RedisUtil redisUtil;

    @Override
    @Transactional//事务回滚
    public ResultBean insert(UserManagement userManagement) {
        ResultBean<String> resultBean = new ResultBean<>();
        try {
            //配置创建信息
            userManagement.setUserId(UUIDUtils.getUUID());
            userManagement.setUserType(0);//普通用户
            userManagement.setIsDelete(0);
            userManagementDao.insert(userManagement);
            logger.info("新增用户信息成功！");
            //配置返回值信息
            resultBean.setData(userManagement.getUserAccount());
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("新增用户信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    @Transactional//事务回滚
    public ResultBean edit(UserManagement userManagement) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try{
            int cont = userManagementDao.updateById(userManagement);
            logger.info("修改用户信息成功！");
            //配置返回值信息
            resultBean.setData(cont);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("修改用户信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    public ResultBean selectUserMessageAccount(String userName) {
        ResultBean<UserManagement> resultBean = new ResultBean<>();
        try{
            //查询用户信息
            UserManagement userManagement = userManagementDao.selectAllByUserAccountUserManagement(userName);
            logger.info("查询用户信息成功！");
            //查询登录状态
            //redisUtil.get(userManagement.getUserAccount());
            if (null == redisUtil.get(userManagement.getUserAccount())){
                //未登录状态
                userManagement.setUserPasword("userOffline");
            }else {
                //已经登录状态
                userManagement.setUserPasword("userOnline");
            }
            logger.info("查询用户登录状态成功！");
            //配置返回值信息
            resultBean.setData(userManagement);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("查询用户信息失败/用户未注册！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    @Transactional//事务回滚
    public ResultBean userLogin(UserManagement userManagement) {
        ResultBean<String> resultBean = new ResultBean<>();
        try {
            //查询用户是否存且密码正确在(查询失败报错走catch逻辑)
            UserManagement user = userManagementDao.selectAllByUserAccountUserManagement(userManagement.getUserAccount());
            //true密码正确,false密码错误
            boolean equals = user.getUserPasword().equals(userManagement.getUserPasword());
            //判断用户是否已经登录(null:未登录,false:已登录)
            //redisUtil.get(userManagement.getUserAccount());
            if(equals) {
                if (null == redisUtil.get(userManagement.getUserAccount())) {
                    //未登录状态添加登录信息(时间为50分钟)
                    redisUtil.set(userManagement.getUserAccount(), userManagement.getUserPasword(), 30000);
                } else {
                    //已经登录状态重置登录过期时间(时间为50分钟)
                    redisUtil.persist(userManagement.getUserAccount());
                    redisUtil.expire(userManagement.getUserAccount(), 30000);
                }
                //配置返回值信息
                resultBean.setData("登录成功！");
            }else {
                //配置返回值信息
                resultBean.setData("密码错误！");
            }
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("用户登录失败/用户未注册！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    @Transactional//事务回滚
    public ResultBean userLogout(UserManagement userManagement) {
        ResultBean<String> resultBean = new ResultBean<>();
        try {
            //判断用户是否已经登录(null:未登录,false:已登录)
            //redisUtil.get(userManagement.getUserAccount());
            if (null == redisUtil.get(userManagement.getUserAccount())) {
                logger.info("用户未登录！");
                resultBean.setData("用户未登录");
                resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
                resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            }else {
                //登出当前用户(将时间设置为1s)
                redisUtil.persist(userManagement.getUserAccount());
                redisUtil.expire(userManagement.getUserAccount(), 1);
                logger.info("用户已退出！");
                resultBean.setData("用户已退出");
                resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
                resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            }
        }catch (Exception e){
            logger.error("登出发生错误！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }
}
