package com.system.xysmartassistants.service;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import com.system.xysmartassistants.domain.entity.InsuranceProductManagement;

/**
 * <p>
 * 保险产品表 服务类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsuranceProductManagementService {

    /**
     * 新增保险产品信息
     *
     * @param insuranceProduct
     * @return
     */
    ResultBean insert(InsuranceProductManagement insuranceProduct);

    /**
     * 编辑/删除保险产品信息
     *
     * @param insuranceProduct
     * @return
     */
    ResultBean edit(InsuranceProductManagement insuranceProduct);

    /**
     * 分页查询保险产品信息
     *
     * @param insuranceProduct
     * @return
     */
    ResultBean<PageInfo<InsuranceProductManagement>> selectProductByPage(InsuranceProductManagement insuranceProduct);

}
