package com.system.xysmartassistants.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户文件信息实体
 *
 * @author xiaoyue
 * @since 2023-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户文件信息实体")
public class UserFileManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * file_id
     */
    @ApiModelProperty(value = "file_id")
    private String fileId;

    /**
     * file_name
     */
    @ApiModelProperty(value = "file_name")
    private String fileName;

    /**
     * file_url
     */
    @ApiModelProperty(value = "file_url")
    private String fileUrl;

    /**
     * creator_id
     */
    @ApiModelProperty(value = "creator_id")
    private String creatorId;

    /**
     * creator_account
     */
    @ApiModelProperty(value = "creator_account")
    private String creatorAccount;

    /**
     * creator_date
     */
    @ApiModelProperty(value = "creator_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatorDate;

    /**
     * editor_id
     */
    @ApiModelProperty(value = "editor_id")
    private String editorId;

    /**
     * editor_account
     */
    @ApiModelProperty(value = "editor_account")
    private String editorAccount;

    /**
     * editor_date
     */
    @ApiModelProperty(value = "editor_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editorDate;


}
