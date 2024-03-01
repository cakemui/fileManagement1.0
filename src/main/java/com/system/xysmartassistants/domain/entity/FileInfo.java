package com.system.xysmartassistants.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * 小月
 * 2024.3.1.10点10分
 *
 * 文件实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件名称
     */
    private String fileName;
}
