package com.example.demo.service;

import com.example.demo.common.ResultBean;
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

    ResultBean<String> filesSelect(String id);
}
