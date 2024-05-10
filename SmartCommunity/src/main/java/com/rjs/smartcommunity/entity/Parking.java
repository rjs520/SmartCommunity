package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 车位实体类
 *
 * @author rjs
 */
@Setter
@Getter
@Schema(description = "车位实体类")
public class Parking implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    /** 车位ID */
    @Schema(description = "车位ID", example = "1")
    private Integer id;

    /** 车位所在地点 */
    @Schema(description = "车位所在地点", example = "1号楼1单元101室")
    private String address;

    /** 车位描述信息 */
    @Schema(description = "车位描述信息", example = "车位位置在1号楼1单元101室的地下车库")
    private String descr;

    /** 车位位置图片的URL或路径 */
    @Schema(description = "车位位置图片的URL或路径", example = "http://www.xxx.com/xxx.jpg")
    private String img;

    /** 车位信息最后更新时间 */
    @Schema(description = "车位信息最后更新时间", example = "2021-07-01 12:00:00")
    private String time;

    /** 车位状态 */
    @Schema(description = "车位状态", example = "待审核")
    private String status;
}
