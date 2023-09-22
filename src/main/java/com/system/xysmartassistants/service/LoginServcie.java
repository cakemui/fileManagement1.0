package com.system.xysmartassistants.service;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.domain.model.UserBusinessSevice;

import java.util.Map;

public interface LoginServcie {
    public ResultBean<Map<String, String>> login(UserBusinessSevice user);
}
