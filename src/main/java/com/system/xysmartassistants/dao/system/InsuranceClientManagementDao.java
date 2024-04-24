package com.system.xysmartassistants.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.system.xysmartassistants.domain.entity.InsuranceClientManagement;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 保险客户管理表 Mapper 接口
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsuranceClientManagementDao extends BaseMapper<InsuranceClientManagement> {

    Page<InsuranceClientManagement> selectAllByPage(InsuranceClientManagement client);

}
