package com.system.xysmartassistants.controller.filesystem;

import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.dao.filesystem.UserFileManagementDao;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.FileManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 小月
 * 2023.8.31.09点51分
 *
 * 文件管理控制层
 */
@RestController
@RequestMapping("/FileManagement")
@Api(tags = "文件管理相关接口", value = "FileManagementController")
public class FileManagementController {

    private final Logger logger = LoggerFactory.getLogger(FileManagementController.class);

    @Resource
    FileManagementService fileManagementService;

    /**
     * 文件上传接口
     * @param file 文件
     * @return
     */
    @PostMapping(value = "/fileUpload")
    @ApiOperation(value = "文件上传接口", notes = "文件上传接口")
    public ResultBean fileUpload(@RequestPart("file") MultipartFile file){
        Assert.notNull(file, "file不可为空！");
        logger.info("====================调用接口====================");
        logger.info("文件上传接口：");
        ResultBean resultBean = fileManagementService.upLoadFile(file);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     * 通过id下载文件接口
     * @param fileId 文件id
     * @return
     */
    @GetMapping(value = "/fileDownloadByFileId")
    @ApiOperation(value = "根据文件ID下载文件接口", notes = "根据文件ID下载文件接口", produces = MediaType.APPLICATION_JSON_VALUE)
    public void fileDownloadByFileId(@RequestParam String fileId, HttpServletRequest request, HttpServletResponse response){
        Assert.notNull(fileId, "fileId不可为空！");
        logger.info("====================调用接口====================");
        logger.info("根据文件ID下载文件接口：");
        fileManagementService.downLoadFile(fileId, request, response);
        logger.info("====================调用结束====================");
    }

    /**
     * 文件批量上传接口
     * @param files 多个文件
     * @return
     */
    @PostMapping(value = "/filesUploads")
    @ApiOperation(value = "文件批量上传接口", notes = "文件批量上传接口")
    public ResultBean filesUpload(@RequestPart("files") MultipartFile[] files){
        Assert.notNull(files, "file不可为空！");
        logger.info("====================调用接口====================");
        logger.info("文件批量上传接口：");
        ResultBean resultBean = fileManagementService.upLoadFiles(files);
        logger.info("====================调用结束====================");
        return resultBean;
    }

    /**
     *分页查询已上传文件接口
     * @param userFileManagement 用户文件信息对象
     * @return
     */
    @PostMapping(value = "/queryFilesByPage")
    @ApiOperation(value = "分页查询已上传文件接口", notes = "分页查询已上传文件接口")
    public ResultBean<PageInfo<UserFileManagement>> queryFilesByPage(@RequestBody UserFileManagement userFileManagement){
        Assert.notNull(userFileManagement, "id不可为空！");
        logger.info("====================调用接口====================");
        logger.info("分页查询已上传文件接口：");
        ResultBean resultBean = fileManagementService.queryFilesByPage(userFileManagement);
        logger.info("====================调用结束====================");
        return resultBean;
    }
}
