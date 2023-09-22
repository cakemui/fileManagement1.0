package com.system.xysmartassistants.service.imp;

import com.system.xysmartassistants.common.ResultBean;
import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.domain.config.SecuredUser;
import com.system.xysmartassistants.domain.model.UserBusinessSevice;
import com.system.xysmartassistants.service.LoginServcie;
import io.lettuce.core.support.caching.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//登录接口
@Service
public class LoginServiceImpl implements LoginServcie {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResultBean<Map<String, String>> login(UserBusinessSevice user) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserAccount(),user.getUserPasswd());
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("用户名或密码错误");
//        }
//        //使用userid生成token
//        SecuredUser loginUser = (SecuredUser) authenticate.getPrincipal();
//        String userId = loginUser.getUserId().toString();
//        String jwt = JwtUtil.createJWT(userId);
//        //authenticate存入redis
//        redisCache.put("login:"+userId,loginUser);
//        //把token响应给前端
//        HashMap<String,String> map = new HashMap<>();
//        map.put("token",jwt);
//
//        ResultBean<Map<String, String>> resultBean = new ResultBean<>();
//        resultBean.setCode(ResultConstant.USER_SUCCESS_MSG_CODE);
//        resultBean.setSmg(ResultConstant.USER_SUCCESS_MSG);
//        resultBean.setData(map);
//        resultBean.setCount(1);
//        return resultBean;
        return null;
    }
}
