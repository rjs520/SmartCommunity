package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 反馈与建议提出表
 *
 * @author rjs
 */
@Schema(description = "反馈与建议提出表")
@Setter
@Getter
public class Recs {
    /** ID */
    @Schema(description = "ID", example = "1")
    private Integer id;

    /** 反馈与建议id */
    @Schema(description = "反馈与建议id", example = "1")
    private Integer csId;

    /** 用户id */
    @Schema(description = "用户id", example = "1")
    private Integer userId;

    /** 反馈与建议内容 */
    @Schema(description = "反馈与建议内容", example = "反馈与建议内容")
    private String content;

    /** 反馈与建议时间 */
    @Schema(description = "反馈与建议时间", example = "2021-01-01 00:00:00")
    private String time;

    /** 反馈状态 */
    @Schema(description = "反馈状态", example = "反馈状态")
    private String status;

    /** 回复意见 */
    @Schema(description = "回复意见", example = "回复意见")
    private String reason;

    /** 反馈与建议标题 */
    @Schema(description = "反馈与建议标题", example = "反馈与建议标题")
    private String csName;

    /** 用户名 */
    @Schema(description = "用户名", example = "用户名")
    private String userName;
}
