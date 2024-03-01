package com.system.xysmartassistants.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.common.UUIDUtils;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.filesystem.UserFileManagementDao;
import com.system.xysmartassistants.domain.model.UserFileManagement;
import com.system.xysmartassistants.service.FileManagementService;
import com.system.xysmartassistants.service.common.FileServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 小月
 * 2023/9/4/14点07分
 *
 * 文件管理类实现
 */
@Service("FileManagementService")
public class FileManagementServiceImpl implements FileManagementService {

    private final Logger logger = LoggerFactory.getLogger(FileManagementService.class);

    @Resource
    UserFileManagementDao userFileManagementDao;

    @Resource
    FileServiceAPI fileServiceAPI;

    @Override
    public ResultBean<String> upLoadFile(MultipartFile file) {
        ResultBean<String> resultBean = new ResultBean<>();
        String fileId = "";

        try {
            logger.info("正在上传文件:" + file.getOriginalFilename());

            //上传文件名称
            String fileName = file.getOriginalFilename();
            if (!fileName.isEmpty()){
                String fileType = fileName.substring(fileName.lastIndexOf("."));
                //根据文件类型生成上传文件路径
                String filePath = "C:/files/" + fileType + "/";
                //生成文件对象
                File save = new File(filePath, fileName);
                //如果路径不存在就创建一个路径
                if (!save.getParentFile().exists()) {
                    save.getParentFile().mkdirs();
                }
                /**
                 * 将上传文件保存到一个目标文件中
                 */
                file.transferTo(new File(filePath + File.separator + fileName));

                fileId = UUIDUtils.getUUID();
            }

            //将数据插入数据库
            UserFileManagement userFileManagement = new UserFileManagement();
            userFileManagement.setFileId(fileId);
            userFileManagement.setFileName(fileName);
            userFileManagement.setFileUrl("XXXX");
            userFileManagement.setIsDelete(0);

            userFileManagement.setCreatorDate(new Date());
            
            userFileManagement.setEditorDate(new Date());
            userFileManagementDao.insert(userFileManagement);

            logger.info("上传文件成功！");
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);

            resultBean.setData(fileId);
        }catch (Exception e){
            logger.error("文件上传失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }

        return resultBean;
    }

    @Override
    public ResultBean<List<String>> upLoadFiles(MultipartFile[] files) {
        ResultBean<List<String>> resultBean = new ResultBean<>();
        List<String> fileIds = new ArrayList<>();
        //计算上传了几个文件
        int i = 0;

        try {
            //循环单个上传
            for(MultipartFile file : files) {
                String fileid = this.upLoadFile(file).getData();
                fileIds.add(fileid);
                i++;
            }

            //配置返回值
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
            resultBean.setData(fileIds);
            resultBean.setCount(i);
        }catch (Exception e){
            logger.error("文件上传失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }

        return resultBean;
    }

    @Override
    public void downLoadFile(String fileId, HttpServletRequest request, HttpServletResponse response) {
        //不需要封装返回消息，框架自带
        try {
            //根据文件ID获取文件地址
            UserFileManagement userFileManagement = this.userFileManagementDao.selectAllByFileId(fileId);
            //下载文件
            fileServiceAPI.download(userFileManagement.getFileUrl(), request, response);
        }catch (Exception e){
            logger.error("文件下载失败！", e);
        }
    }

    @Override
    public ResultBean<String> queryFileById(String id) {
        ResultBean<String> resultBean = new ResultBean<>();

        try {
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);

            //逻辑未实现
//            resultBean.setData();
        }catch (Exception e){
            logger.error("查询失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }

        return resultBean;
    }

    @Override
    public ResultBean<PageInfo<UserFileManagement>> queryFilesByPage(UserFileManagement userFileManagement) {
        ResultBean<PageInfo<UserFileManagement>> resultBean = new ResultBean<>();

        try {
            //设定初始分页
            PageHelper.startPage(userFileManagement.getPage(), userFileManagement.getPageSize());
            //封装查询结果为PageInfo类型
            Page<UserFileManagement> page = this.userFileManagementDao.selectAllByPage(userFileManagement);
            PageInfo<UserFileManagement> pageInfo = new PageInfo<>(page);

            //配置返回值
            resultBean.setData(pageInfo);
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
        }
        catch (Exception e){
            logger.error("查询失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }

        return resultBean;
    }
}
