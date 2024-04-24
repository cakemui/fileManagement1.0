package com.system.xysmartassistants.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.system.InsuranceClientManagementDao;
import com.system.xysmartassistants.domain.entity.InsuranceClientManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.InsuranceClientManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 保险客户管理表 服务实现类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Service("InsuranceClientManagementService")
public class InsuranceClientManagementServiceImpl implements InsuranceClientManagementService {

    private final Logger logger = LoggerFactory.getLogger(InsuranceClientManagementServiceImpl.class);

    @Resource
    InsuranceClientManagementDao insuranceClientManagementDao;

    @Override
    @Transactional//事务回滚
    public ResultBean insert(InsuranceClientManagement client) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            //配置创建信息
            client.setClientId(UUIDUtils.getUUID());
            client.setIsDelete(0);
            int insert = insuranceClientManagementDao.insert(client);
            logger.info("新增用户信息成功！");
            //配置返回值信息
            resultBean.setData(insert);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("新增客户信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    @Transactional//事务回滚
    public ResultBean edit(InsuranceClientManagement client) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            int cont = insuranceClientManagementDao.updateById(client);
            logger.info("修改客户信息成功！");
            //配置返回值信息
            resultBean.setData(cont);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("修改客户信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    public ResultBean<PageInfo<InsuranceClientManagement>> selectClientByPage(InsuranceClientManagement client) {
        ResultBean<PageInfo<InsuranceClientManagement>> resultBean = new ResultBean<>();
        try{
            //设定初始分页
            PageHelper.startPage(client.getPage(), client.getPageSize());
            client.setIsDelete(0);
            //封装查询结果为PageInfo类型
            Page<InsuranceClientManagement> page = this.insuranceClientManagementDao.selectAllByPage(client);
            PageInfo<InsuranceClientManagement> pageInfo = new PageInfo<>(page);
            //配置返回值
            logger.info("查询成功");
            resultBean.setData(pageInfo);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
        }catch (Exception e){
            logger.error("查询失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }
        return resultBean;
    }
}
