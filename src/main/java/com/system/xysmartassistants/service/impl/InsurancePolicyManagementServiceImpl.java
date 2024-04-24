package com.system.xysmartassistants.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.system.InsurancePolicyManagementDao;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import com.system.xysmartassistants.service.InsurancePolicyManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 保单管理表 服务实现类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Service("InsurancePolicyManagementService")
public class InsurancePolicyManagementServiceImpl implements InsurancePolicyManagementService {

    private final Logger logger = LoggerFactory.getLogger(InsurancePolicyManagementServiceImpl.class);

    @Resource
    InsurancePolicyManagementDao insurancePolicyManagementDao;

    @Override
    @Transactional//事务回滚
    public ResultBean insert(InsurancePolicyManagement policyManagement) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            //配置创建信息
            policyManagement.setPolicyId(UUIDUtils.getUUID());
            policyManagement.setIsDelete(0);
            int insert = insurancePolicyManagementDao.insert(policyManagement);
            logger.info("新增保单信息成功！");
            //配置返回值信息
            resultBean.setData(insert);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("新增保单信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    @Transactional//事务回滚
    public ResultBean edit(InsurancePolicyManagement policyManagement) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            int cont = insurancePolicyManagementDao.updateById(policyManagement);
            logger.info("修改保单信息成功！");
            //配置返回值信息
            resultBean.setData(cont);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
        }catch (Exception e){
            logger.error("修改保单信息失败！", e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }

    @Override
    public ResultBean<PageInfo<InsurancePolicyManagement>> selectClientByPage(InsurancePolicyManagement policyManagement) {
        ResultBean<PageInfo<InsurancePolicyManagement>> resultBean = new ResultBean<>();
        try{
            //设定初始分页
            PageHelper.startPage(policyManagement.getPage(), policyManagement.getPageSize());
            //封装查询结果为PageInfo类型
            Page<InsurancePolicyManagement> page = this.insurancePolicyManagementDao.selectAllByPage(policyManagement);
            PageInfo<InsurancePolicyManagement> pageInfo = new PageInfo<>(page);
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
