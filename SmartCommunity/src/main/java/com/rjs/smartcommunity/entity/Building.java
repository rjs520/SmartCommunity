package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 楼宇实体类
 *
 * @author rjs
 */
@Schema(description = "楼宇实体类")
@Setter
@Getter
public class Building {
    /** ID */
    @Schema(description = "ID", example = "1")
    private Integer id;

    /** 楼宇名称 */
    @Schema(description = "楼宇名称", example = "1号楼")
    private String name;

    /** 地址 */
    @Schema(description = "地址", example = "XX市XX区XX街道")
    private String address;

    /** 总楼层数 */
    @Schema(description = "总楼层数", example = "20")
    private Integer totalFloors;

    /** 总户数 */
    @Schema(description = "总户数", example = "100")
    private Integer totalHouses;

    /** 物业公司 */
    @Schema(description = "物业公司", example = "XX物业公司")
    private String propertyCompany;

    /** 物业电话 */
    @Schema(description = "物业电话", example = "123456789")
    private String propertyPhone;
}
