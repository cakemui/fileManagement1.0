package com.system.xysmartassistants.service.impl;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.filesystem.UserFileManagementDao;
import com.system.xysmartassistants.dao.system.UserManagementDao;
import com.system.xysmartassistants.domain.entity.UserManagement;
import com.system.xysmartassistants.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    @Override
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
    public ResultBean selectUserMessage(String userName) {
        ResultBean<UserManagement> resultBean = new ResultBean<>();
        try{
            UserManagement userManagement = userManagementDao.selectAllByUserAccountUserManagement(userName);
            userManagement.setUserPasword("***");
            resultBean.setData(userManagement);
            logger.info("查询用户信息成功！");
            //配置返回值信息
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("修改用户信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }
}
