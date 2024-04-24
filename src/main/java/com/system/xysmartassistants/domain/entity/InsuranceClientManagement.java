package com.system.xysmartassistants.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.xysmartassistants.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 保险客户管理表
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InsuranceClientManagement extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableId(value = "client_id", type = IdType.UUID)
    private String clientId;

    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 客户年龄
     */
    private Long clientAge;

    /**
     * 客户性别(0:男;1:女)
     */
    private Integer clientSex;

    /**
     * 客户生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clientBirthday;

    /**
     * 手机号码
     */
    private String phoneNumber;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editorDate;

    /**
     * 是否删除(0:正常,1:删除)
     */
    private Integer isDelete;


}
