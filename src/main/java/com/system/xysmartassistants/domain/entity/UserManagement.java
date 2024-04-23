package com.system.xysmartassistants.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户管理表
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
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
    private String userPasword;

    /**
     * 用户类型(0:普通用户;1:管理员;2:停用)
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
