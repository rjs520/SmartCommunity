package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * NurseHouse 实体类，用于表示养老院的基本信息。
 *
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "养老院实体类")
public class NurseHouse implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 实体ID */
    @Schema(description = "实体ID", example = "1")
    private Integer id;

    /** 名称 */
    @Schema(description = "名称", example = "养老院")
    private String name;

    /** 价格信息 */
    @Schema(description = "价格信息", example = "1000")
    private String price;

    /** 图片链接或路径 */
    @Schema(description = "图片链接或路径", example = "/images/nurse.jpg")
    private String img;

    /** 地址详情 */
    @Schema(description = "地址详情", example = "广东省深圳市南山区")
    private String address;

    /** 浏览次数 */
    @Schema(description = "浏览次数", example = "100")
    private Integer count;

    /** 标签，用于快速识别或分类 */
    @Schema(description = "标签", example = "养老院")
    private String tags;

    /** 床位数量 */
    @Schema(description = "床位数量", example = "100")
    private String beds;

    /** 性质，例如：公立、私立 */
    @Schema(description = "性质", example = "公立")
    private String nature;

    /** 类型，例如：养老院、护理院 */
    @Schema(description = "类型", example = "养老院")
    private String type;

    /** 详细描述 */
    @Schema(description = "详细描述", example = "这是一家养老院")
    private String content;

    /** 面积，单位平方米 */
    @Schema(description = "面积", example = "1000")
    private String area;

    /** 成立日期 */
    @Schema(description = "成立日期", example = "2021-01-01")
    private String date;

    /** 联系方式，电话或手机号 */
    @Schema(description = "联系方式", example = "13800138000")
    private String phone;

    /** 经度，用于地图定位 */
    @Schema(description = "经度", example = "113.123456")
    private String longitude;

    /** 纬度，用于地图定位 */
    @Schema(description = "纬度", example = "23.123456")
    private String latitude;
}
