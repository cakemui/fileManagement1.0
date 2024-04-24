package com.system.xysmartassistants.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.system.InsuranceProductManagementDao;
import com.system.xysmartassistants.domain.entity.InsuranceProductManagement;
import com.system.xysmartassistants.service.InsuranceProductManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 保险产品表 服务实现类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Service("InsuranceProductManagementService")
public class InsuranceProductManagementServiceImpl implements InsuranceProductManagementService {

    private final Logger logger = LoggerFactory.getLogger(InsuranceProductManagementServiceImpl.class);

    @Resource
    InsuranceProductManagementDao insuranceProductManagementDao;

    @Override
    @Transactional//事务回滚
    public ResultBean insert(InsuranceProductManagement productManagement) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            //配置创建信息
            productManagement.setProductId(UUIDUtils.getUUID());
            productManagement.setIsDelete(0);
            int insert = insuranceProductManagementDao.insert(productManagement);
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
    public ResultBean edit(InsuranceProductManagement productManagement) {
        ResultBean<Integer> resultBean = new ResultBean<>();
        try {
            int cont = insuranceProductManagementDao.updateById(productManagement);
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
    public ResultBean<PageInfo<InsuranceProductManagement>> selectProductByPage(InsuranceProductManagement productManagement) {
        ResultBean<PageInfo<InsuranceProductManagement>> resultBean = new ResultBean<>();
        try{
            //设定初始分页
            PageHelper.startPage(productManagement.getPage(), productManagement.getPageSize());
            productManagement.setIsDelete(0);
            //封装查询结果为PageInfo类型
            Page<InsuranceProductManagement> page = this.insuranceProductManagementDao.selectAllByPage(productManagement);
            PageInfo<InsuranceProductManagement> pageInfo = new PageInfo<>(page);
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
