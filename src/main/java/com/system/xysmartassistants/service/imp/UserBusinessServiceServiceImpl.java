package com.system.xysmartassistants.service.imp;

import com.system.xysmartassistants.domain.model.UserBusinessSevice;
import com.system.xysmartassistants.dao.filesystem.UserBusinessServiceDao;
import com.system.xysmartassistants.service.UserBusinessServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限服务表 服务实现类
 * </p>
 *
 * @author xiaoyue
 * @since 2023-09-22
 */
@Service
public class UserBusinessServiceServiceImpl extends ServiceImpl<UserBusinessServiceDao, UserBusinessSevice> implements UserBusinessServiceService {

}
