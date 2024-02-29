package com.system.xysmartassistants.service;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 小月
 * 2023/9/4/14点07分
 *
 * 文件管理业务接口
 */
public interface FileManagementService {

    ResultBean<String> upLoadFile(MultipartFile file);

    ResultBean<String> downLoadFile(String fileId);

    ResultBean<List<String>> upLoadFiles(MultipartFile[] files);

    ResultBean<String> queryFileById(String id);

    ResultBean<PageInfo<UserFileManagement>> queryFilesByPage(UserFileManagement userFileManagement);
}
