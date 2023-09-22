package com.system.xysmartassistants.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限服务表
 * </p>
 *
 * @author xiaoyue
 * @since 2023-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户文件信息实体")
public class UserBusinessSevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPasswd;

    /**
     * 用户类型(0:普通用户,1:管理员)
     */
    private Integer userType;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建账户
     */
    private String creatorAccount;

    /**
     * 创建时间
     */
    private Date creatorDate;

    /**
     * 修改人ID
     */
    private String editorId;

    /**
     * 修改账户
     */
    private String editorAccount;

    /**
     * 修改时间
     */
    private Date editorDate;

    /**
     * 是否删除(0:正常,1:删除)
     */
    private Integer isDelete;


}
