package com.system.xysmartassistants.service.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 小月
 * 2024.3.1.11点30分
 *
 * 文件公共服务接口
 */
public interface FileServiceAPI {

    public void upload(String fileUrl, HttpServletRequest request, HttpServletResponse response);

    public void download(String fileUrl, HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void downloads(String fileUrl) throws IOException;
}
