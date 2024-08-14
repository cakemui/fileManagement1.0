package com.system.xysmartassistants.service.impl;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.service.ClientSpiderService;
import com.system.xysmartassistants.utils.ClientHttpUtil;
import com.system.xysmartassistants.utils.ClientSpiderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    ClientHttpUtil clientHttpUtil;

    @Scheduled(cron = "0/30 * * * * ?")
    @Override
    public ResultBean GetFileServiceURL() {
        ResultBean resultBean = new ResultBean<>();
        try{
            //创建登录数据对象
            Map<String,Object> map = new HashMap<>();
            map.put("email","1656629680@qq.com");
            map.put("password","0930Chen");
            //模拟登录页面
            clientHttpUtil.sendPost("http://localhost:9200/api/v1/user/login",map);
            //捕获页面
            String response = clientHttpUtil.sendGet("http://localhost:9200/api/v1/tunnels");
            logger.info(response);
        }catch (Exception e){
            logger.error("获取地址信息失败！",e);
            resultBean.setCode(ResultConstant.USER_ERROR_MSG_CODE);
            resultBean.setSmg(ResultConstant.USER_ERROR_MSG);
        }
        return resultBean;
    }
}
