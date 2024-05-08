package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动实体类
 *
 * @author rjs @Schema 对应整个实体在OpenAPI文档中的描述
 */
@Schema(name = "Activity", description = "活动实体")
@Setter
@Getter
public class Activity implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 活动ID @Schema 对应属性在OpenAPI文档中的描述 */
    @Schema(name = "id", description = "活动ID", example = "1")
    private Integer id;

    /** 活动名称 */
    @Schema(name = "name", description = "活动名称", example = "夏日音乐节")
    private String name;

    /** 活动封面图片地址 */
    @Schema(
            name = "cover",
            description = "活动封面图片地址",
            example = "https://example.com/activity-cover.jpg")
    private String cover;

    /** 活动简介 */
    @Schema(name = "description", description = "活动简介", example = "一场夏日的音乐盛宴")
    private String descr;

    /** 活动开始日期，格式为自定义字符串 */
    @Schema(name = "start", description = "活动开始日期", example = "2023-07-01")
    private String start;

    /** 活动结束日期，格式为自定义字符串 */
    @Schema(name = "end", description = "活动结束日期", example = "2023-07-31")
    private String end;

    /** 活动详细内容 */
    @Schema(name = "content", description = "活动详细内容")
    private String content;

    /** 活动举办地址 */
    @Schema(name = "address", description = "活动举办地址")
    private String address;

    /** 活动状态 */
    @Schema(name = "status", description = "活动状态")
    private String status;
}
