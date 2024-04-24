package com.system.xysmartassistants.dao.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.system.xysmartassistants.domain.entity.InsuranceProductManagement;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 保险产品表 Mapper 接口
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
public interface InsuranceProductManagementDao extends BaseMapper<InsuranceProductManagement> {

    Page<InsuranceProductManagement> selectAllByPage(InsuranceProductManagement insuranceProductManagement);

}
