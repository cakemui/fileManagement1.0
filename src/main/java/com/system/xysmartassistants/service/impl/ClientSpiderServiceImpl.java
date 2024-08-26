package com.system.xysmartassistants.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.service.ClientSpiderService;
import com.system.xysmartassistants.utils.ClientHttpUtil;
import com.system.xysmartassistants.utils.DingtalkRobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 网页爬虫 服务实现类
 * </p>
 *
 * @author xiaoyue
 * @since 2024-08-14
 */
@Service("ClientSpiderService")
@EnableScheduling
public class ClientSpiderServiceImpl implements ClientSpiderService {

    private final Logger logger = LoggerFactory.getLogger(ClientSpiderServiceImpl.class);

    @Scheduled(cron = "0 0/30 * * * ? ")
    @Override
    public ResultBean GetFileServiceURL() {
        ClientHttpUtil clientHttpUtil = new ClientHttpUtil();

        ResultBean resultBean = new ResultBean<>();
        try{
            //创建登录数据对象
            Map<String,Object> map = new HashMap<>();
            map.put("email","1656629680@qq.com");
            map.put("password","0930Chen");
            //模拟登录页面获取token
            String sendPost = clientHttpUtil.sendPost("http://localhost:9200/api/v1/user/login", map);
            //转换返回值类型为json并获取token
            JSONObject tokenJsonObject = JSONObject.parseObject(sendPost);
            tokenJsonObject = tokenJsonObject.getJSONObject("data");
            String token = tokenJsonObject.getString("token");
            logger.info("获取当前token为:" + token);
            //设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("content-type", "application/json");
            headers.put("Authorization", "Bearer " + token);
            //设置参数
            Map<String, Object> infoGetParams = new HashMap<>();
            infoGetParams.put("token", token);
            //设置cookies
            Map<String, String> cookies = new HashMap<>();
            cookies.put("vue_admin_template_token", token);
//            //获取用户信息
//            String infoGet = clientHttpUtil.sendGet("http://localhost:9200/api/v1/user/info", headers, infoGetParams, cookies, "UTF-8");
            //使用token获取数据
            String response = clientHttpUtil.sendGet("http://localhost:9200/api/v1/tunnels",headers , null, cookies,"UTF-8");
            //转换返回值类型为json并获取token
            JSONObject fileJsonObject = JSONObject.parseObject(response);
            fileJsonObject = fileJsonObject.getJSONObject("data");
            JSONArray fileJsonArray = JSONArray.parseArray(fileJsonObject.getString("items"));
            fileJsonObject = fileJsonArray.getJSONObject(0);
            fileJsonArray = JSONArray.parseArray(fileJsonObject.getString("publish_tunnels"));
            fileJsonObject = fileJsonArray.getJSONObject(1);
            String fileURL = fileJsonObject.getString("public_url");
            logger.info("获取文件地址信息为:" + fileURL);
            //使用机器人发消息到钉钉
            DingtalkRobot dingtalkRobot = new DingtalkRobot();
            dingtalkRobot.textMsg( "公共文件服务地址为：\r\n" + fileURL, null, null, null);
        }catch (Exception e){
            logger.error("获取地址信息失败！",e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }
}
