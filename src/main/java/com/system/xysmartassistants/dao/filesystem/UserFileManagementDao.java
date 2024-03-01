package com.system.xysmartassistants.dao.filesystem;

import com.github.pagehelper.Page;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户文件管理 Mapper 接口
 * </p>
 *
 * @author xiaoyue
 * @since 2023-09-18
 */
public interface UserFileManagementDao extends BaseMapper<UserFileManagement> {

    Page<UserFileManagement> selectAllByPage(@Param("userFileManagement") UserFileManagement userFileManagement);

    UserFileManagement selectAllByFileId(@Param("fileId")String fileId);
}
