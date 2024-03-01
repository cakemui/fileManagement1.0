package com.system.xysmartassistants.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 小月
 * 2024.3.1.10点10分
 *
 * 下载文件实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DownloadFileInfo {

    /**
     * 文件总大小
     */
    private long fSize;

    /**
     * 断点起始位置
     */
    private long pos;

    /**
     * 断点结束位置
     */
    private long last;

    /**
     * rang响应
     */
    private long rangeLength;

    /**
     * range响应
     */
    private String contentRange;
}
