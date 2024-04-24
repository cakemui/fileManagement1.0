package com.system.xysmartassistants.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.system.xysmartassistants.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 保单管理表
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InsurancePolicyManagement extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保单id
     */
    @TableId(value = "policy_id", type = IdType.UUID)
    private String policyId;

    /**
     * 保单号
     */
    private String policyNumber;

    /**
     * 保险种类
     */
    private String insuranceTypei;

    /**
     * 被保人
     */
    private String insured;

    /**
     * 生效日期
     */
    private Date effectiveDate;

    /**
     * 满期日期
     */
    private Date expirationDate;

    /**
     * 保额
     */
    private Double insuredAmount;

    /**
     * 保费
     */
    private Double premium;

    /**
     * 缴费年限
     */
    private Integer paymentPeriod;

    /**
     * 受益人
     */
    private String beneficiary;

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
