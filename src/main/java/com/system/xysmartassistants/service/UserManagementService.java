package com.system.xysmartassistants.service;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.entity.UserManagement;

/**
 * <p>
 * 用户管理表 服务类
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface UserManagementService {

    /**
     * 新增用户
     *
     * @param userManagement
     * @return
     */
    ResultBean insert (UserManagement userManagement);

    /**
     * 修改/删除用户信息
     *
     * @param userManagement
     * @return
     */
    ResultBean edit (UserManagement userManagement);

    /**
     * 通过用户账号获取登陆状态
     *
     * @param userName
     * @return
     */
    ResultBean selectUserMessage (String userName);

}
