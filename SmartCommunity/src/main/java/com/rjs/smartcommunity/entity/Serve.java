package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 社区服务实体类
 *
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "社区服务实体类")
public class Serve implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 服务ID */
    @Schema(description = "服务ID")
    private Integer id;

    /** 服务名称 */
    @Schema(description = "服务名称")
    private String name;

    /** 服务封面图片地址 */
    @Schema(description = "服务封面图片地址")
    private String cover;

    /** 服务详细描述 */
    @Schema(description = "服务详细描述")
    private String descr;

    /** 服务发布时间 */
    @Schema(description = "服务发布时间")
    private String date;
}
