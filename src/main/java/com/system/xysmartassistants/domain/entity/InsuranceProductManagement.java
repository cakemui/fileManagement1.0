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
 * 保险产品表
 * </p>
 *
 * @author haona
 * @since 2024-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InsuranceProductManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "product_id", type = IdType.UUID)
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品介绍
     */
    private String productIntroduction;

    /**
     * 保险种类
     */
    private String insuranceTypei;

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
