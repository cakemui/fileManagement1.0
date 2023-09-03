package com.example.demo.service.imp;

import com.example.demo.common.ResultBean;
import com.example.demo.common.UUIDUtils;
import com.example.demo.constant.ResultConstant;
import com.example.demo.service.FileManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("FileManagementService")
public class FileManagementImp implements FileManagementService {

    private final Logger logger = LoggerFactory.getLogger(FileManagementService.class);

    @Override
    public ResultBean<String> upLoadFile(MultipartFile file) {
        ResultBean<String> resultBean = new ResultBean<>();
        String fileId = "";

        try {
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);

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
    public ResultBean<String> downLoadFile(String FileId) {
        ResultBean<String> resultBean = new ResultBean<>();

        try {
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);

        }catch (Exception e){
            logger.error("文件下载失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }
        return resultBean;
    }

    @Override
    public ResultBean<String> filesSelect(String id) {
        ResultBean<String> resultBean = new ResultBean<>();

        try {
            resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
            resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
//            resultBean.setData();
        }catch (Exception e){
            logger.error("查询失败！", e);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
        }

        return resultBean;
    }
}
