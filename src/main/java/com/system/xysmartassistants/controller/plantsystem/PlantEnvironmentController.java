package com.system.xysmartassistants.controller.plantsystem;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小月
 * 2024年7月8日11点11分
 *
 * 植物环境控制层
 */
@RestController
@RequestMapping("/PlantEnvironment")
@Api(tags = "植物环境相关接口", value = "PlantEnvironmentController")
public class PlantEnvironmentController {

    private final Logger logger = LoggerFactory.getLogger(PlantEnvironmentController.class);

    // TODO: 2024/7/8 1.数据接受功能
    // TODO: 2024/7/8 2.基础实时数据查询（webSocket）
    // TODO: 2024/7/8 3.历史数据报表
    // TODO: 2024/7/8 4.钉钉机器人数据提醒 (根据文档封装各种类型消息到service中)


}
