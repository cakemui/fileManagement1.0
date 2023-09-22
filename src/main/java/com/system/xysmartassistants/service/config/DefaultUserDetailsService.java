package com.system.xysmartassistants.service.config;

import com.system.xysmartassistants.constant.ResultConstant;
import com.system.xysmartassistants.dao.filesystem.UserBusinessServiceDao;
import com.system.xysmartassistants.domain.config.SecuredUser;
import com.system.xysmartassistants.domain.model.UserBusinessSevice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    UserBusinessServiceDao userBusinessServiceDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        UserBusinessSevice userBusinessSevice = userBusinessServiceDao.selectByUserName(username);
        if (Objects.isNull(userBusinessSevice)) {
            // User with the above username not found , login failed
            throw new UsernameNotFoundException(ResultConstant.USER_ID_ERROR_MSG);
        }
        try {
            //设置用户信息
            SecuredUser securedUser = new SecuredUser();
            securedUser.setUserId(userBusinessSevice.getUserId());
            securedUser.setUserAccount(userBusinessSevice.getUserAccount());
            securedUser.setUserName(userBusinessSevice.getUserName());
            securedUser.setUserPasswd(userBusinessSevice.getUserPasswd());
            securedUser.setUserType(userBusinessSevice.getUserType());

            securedUser.setAuthorities(getAuthorities(userBusinessSevice.getUserType()));
        } catch (Exception e){

        }


        return new SecuredUser();
    }

    private Collection<GrantedAuthority> getAuthorities(Integer userType){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (userType == 0) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            return authList;
        } else if (userType == 1) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return authList;
        }
        throw new UsernameNotFoundException(ResultConstant.USER_ID_ERROR_MSG);
    }
}
