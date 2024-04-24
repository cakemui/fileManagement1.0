package com.system.xysmartassistants.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.system.xysmartassistants.domain.entity.InsurancePolicyManagement;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 保单管理表 Mapper 接口
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsurancePolicyManagementDao extends BaseMapper<InsurancePolicyManagement> {

    Page<InsurancePolicyManagement> selectAllByPage(InsurancePolicyManagement insurancePolicyManagement);

}
