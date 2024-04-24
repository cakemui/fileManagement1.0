package com.system.xysmartassistants.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.xysmartassistants.domain.entity.UserManagement;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户管理表 Mapper 接口
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface UserManagementDao extends BaseMapper<UserManagement> {

    /**
     * 通过userAccount查询数据
     *
     * @param userAccount
     * @return
     */
    UserManagement selectAllByUserAccountUserManagement(String userAccount);

}
