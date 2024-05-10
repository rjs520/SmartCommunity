package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 公告信息实体类，实现Serializable接口
 *
 * @author rjs
 */
@Setter
@Getter
@Schema(description = "公告信息实体类")
public class Notice implements Serializable {

    /** 序列化版本号 */
    @Serial private static final long serialVersionUID = 1L;

    /** 公告信息ID */
    @Schema(description = "公告信息ID", example = "1")
    private Integer id;

    /** 公告标题 */
    @Schema(description = "公告标题", example = "公告标题")
    private String title;

    /** 公告内容 */
    @Schema(description = "公告内容", example = "公告内容")
    private String content;

    /** 公告创建时间 */
    @Schema(description = "公告创建时间", example = "2021-01-01 12:00:00")
    private String time;

    /** 公告创建人 */
    @Schema(description = "公告创建人", example = "张三")
    private String user;
}
