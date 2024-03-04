package com.system.xysmartassistants.service.common.impl;

import com.system.xysmartassistants.service.FileManagementService;
import com.system.xysmartassistants.service.common.FileServiceAPI;
import com.system.xysmartassistants.utils.FileServieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 小月
 * 2024.3.1.11点30分
 *
 * 文件公共服务实现类
 */
@Service("FileServiceAPI")
public class FileServiceAPIImpl implements FileServiceAPI {

    private final Logger logger = LoggerFactory.getLogger(FileServiceAPI.class);

    @Override
    public void upload(String fileUrl, HttpServletRequest request, HttpServletResponse response) {
        FileServieUtil fileServieUtil = new FileServieUtil();
        try {
            fileServieUtil.setUploadPath(fileUrl);
            fileServieUtil.upload(request, response);
            logger.info("文件上传完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void download(String fileUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileServieUtil fileServieUtil = new FileServieUtil();
        fileServieUtil.setDownloadFile(fileUrl);
        fileServieUtil.download(request, response);
        logger.info("文件下载完成！");
    }

    @Override
    public void downloads(String fileUrl) throws IOException {
        FileServieUtil fileServieUtil = new FileServieUtil();
        fileServieUtil.setDownloadFile(fileUrl);
        fileServieUtil.downloads();
        logger.info("文件下载完成！");
    }
}
