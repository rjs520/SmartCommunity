package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "资讯分类")
public class Category {

    /** 资讯ID */
    @Schema(description = "资讯分类ID")
    private Integer id;

    /** 资讯分类名 */
    @Schema(description = "资讯分类名")
    private String name;
}
