package com.system.xysmartassistants.service;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.InsuranceClientManagement;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;

/**
 * <p>
 * 保单管理表 服务类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsurancePolicyManagementService {

    /**
     * 新增保单信息
     *
     * @param policyManagement
     * @return
     */
    ResultBean insert(InsurancePolicyManagement policyManagement);

    /**
     * 编辑/删除保单信息
     *
     * @param policyManagement
     * @return
     */
    ResultBean edit(InsurancePolicyManagement policyManagement);

    /**
     * 分页查询保单信息
     *
     * @param policyManagement
     * @return
     */
    ResultBean<PageInfo<InsurancePolicyManagement>> selectClientByPage(InsurancePolicyManagement policyManagement);

}
