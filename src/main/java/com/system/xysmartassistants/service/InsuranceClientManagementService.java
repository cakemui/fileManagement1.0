package com.system.xysmartassistants.service;

import ch.qos.logback.core.net.server.Client;
import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.InsuranceClientManagement;
import com.system.xysmartassistants.domain.model.UserFileManagement;

/**
 * <p>
 * 保险客户管理表 服务类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsuranceClientManagementService {

    /**
     * 新增保险客户信息
     *
     * @param client
     * @return
     */
    ResultBean insert(InsuranceClientManagement client);

    /**
     * 编辑/删除保险客户信息
     *
     * @param client
     * @return
     */
    ResultBean edit(InsuranceClientManagement client);

    /**
     * 分页查询保险客户信息
     *
     * @param client
     * @return
     */
    ResultBean<PageInfo<InsuranceClientManagement>> selectClientByPage(InsuranceClientManagement client);

}
