package com.system.xysmartassistants.service;


import com.system.xysmartassistants.common.ResultBean;

/**
 * <p>
 * 网页爬虫 服务类
 * </p>
 *
 * @author lengx
 * @since 2024-08-14
 */
public interface ClientSpiderService {

    /**
     * 获取远程文件服务Url
     * @return resultBean
     */
    ResultBean GetFileServiceURL();
}
