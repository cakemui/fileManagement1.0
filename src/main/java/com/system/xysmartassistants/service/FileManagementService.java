package com.system.xysmartassistants.service;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 小月
 * 2023/9/4/14点07分
 *
 * 文件管理业务接口
 */
public interface FileManagementService {

    ResultBean<String> upLoadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

    ResultBean<String> fileShardingUpload (MultipartFile file, HttpServletRequest request, HttpServletResponse response);

    ResultBean<List<String>> upLoadFiles (MultipartFile[] files);

    void downLoadFile (String fileId, HttpServletRequest request, HttpServletResponse response);

    void downLoadFileSharding (String fileId, HttpServletRequest request, HttpServletResponse response);

    ResultBean<UserFileManagement> queryFileById (String id);

    ResultBean<PageInfo<UserFileManagement>> queryFilesByPage(UserFileManagement userFileManagement);
}
