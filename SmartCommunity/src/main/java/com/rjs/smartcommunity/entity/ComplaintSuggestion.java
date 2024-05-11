package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 投诉与建议实体类
 *
 * @author rjs
 */
@Schema(description = "投诉与建议实体类")
@Setter
@Getter
public class ComplaintSuggestion implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    /** ID */
    @Schema(description = "ID", example = "1")
    private Integer id;

    /** 标题 */
    @Schema(description = "标题", example = "投诉")
    private String name;

    /** 封面 */
    @Schema(description = "封面", example = "http://www.xxx.com/xxx.jpg")
    private String cover;

    /** 描述 */
    @Schema(description = "描述", example = "投诉内容")
    private String descr;

    /** 日期 */
    @Schema(description = "日期", example = "2021-01-01")
    private String date;

    /** 状态 */
    @Schema(description = "状态", example = "已处理")
    private String status;
}
