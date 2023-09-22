package com.system.xysmartassistants.dao.filesystem;

import com.system.xysmartassistants.domain.model.UserBusinessSevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户权限服务表 Mapper 接口
 * </p>
 *
 * @author xiaoyue
 * @since 2023-09-22
 */
public interface UserBusinessServiceDao extends BaseMapper<UserBusinessSevice> {

    public UserBusinessSevice selectByUserName (@Param("userName") String userName);

}
